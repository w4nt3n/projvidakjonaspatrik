/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Person;
import ActiveRecord.PersonDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Vidak
 */
public class JdbcPersonDAO implements PersonDAO {
    private DataSource dataSource;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void insert(Person person){
 
            String sql = "INSERT INTO PERSON " +
                            "(personId, name, surname, age, email, telephone) VALUES (?, ?, ?, ?, ?, ?)";
            Connection conn = null;

            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, person.getPersonId());
                ps.setString(2, person.getName());
                ps.setString(3, person.getSurname());
                ps.setInt(4, person.getAge());
                ps.setString(5, person.getEmail());
                ps.setString(6, person.getTelephone());
                ps.executeUpdate();
                ps.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);

            } finally {
                if (conn != null) {
                    try {
                            conn.close();
                    } catch (SQLException e) {}
                }
            }
	}
 
	public Person findByPersonId(int custId){
 
            String sql = "SELECT * FROM PERSON WHERE personId = ?";

            Connection conn = null;

            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, custId);
                Person person = null;
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                person = new Person(
                    rs.getString("name"), 
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("telephone")
                );
                }
                rs.close();
                ps.close();
                return person;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (conn != null) {
                    try {
                    conn.close();
                    } catch (SQLException e) {}
                }
            }
	}
}
