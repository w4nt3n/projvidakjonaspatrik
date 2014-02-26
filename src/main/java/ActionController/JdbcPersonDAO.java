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
import java.util.ArrayList;
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

    public Person findByPersonId(int personId){

        String sql = "SELECT * FROM PERSON WHERE personId = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, personId);
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
    
    public ArrayList<Person> getAllPersons(){
        
        String sql = "SELECT * FROM PERSON";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Person> resultsArrayList = new ArrayList<Person>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person(
                    rs.getString("name"), 
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("telephone")
                );
                resultsArrayList.add(person);
            }
            rs.close();
            ps.close();
            return resultsArrayList;
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
