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
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, person.getCustId());
			ps.setString(2, person.getName());
			ps.setInt(3, person.getAge());
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
 
	public Person findByCustomerId(int custId){
 
		String sql = "SELECT * FROM PERSON WHERE CUST_ID = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Person person = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                            person = new Person(
                                    rs.getString("NAME"), 
                                    rs.getInt("Age")
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
