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

    /**
     * This function writes a Person to the database. 
     * <p>
     * This method receives a Person and writes it to the database
     * @param person The Person object to be written
     */
    @Override
    public void insert(Person person){

	// The SQL code to be sent
        String sql = "INSERT INTO PERSON " +
                        "(personId, name, surname, birthDate, email, telephone) VALUES (?, ?, ?, ?, ?, ?)";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, person.getPersonId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getSurname());
            ps.setString(4, person.getBirthDate());
            ps.setString(5, person.getEmail());
            ps.setString(6, person.getTelephone());
	    // Execute query
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

    /**
     * This function searches for a person with the specific Id and
     * returns a Person object if found.
     * <p>
     * This method receives the Id of a user and fetches the corresponding
     * user from the database, returning a Person object.
     * @param personId The person Id to be fetched
     * @return A Person object.
     */
    @Override
    public Person findByPersonId(int personId){

	// The SQL code to be sent
        String sql = "SELECT * FROM PERSON WHERE personId = ?";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, personId);
	    // Creates a person to return
            Person person = null;
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a person that matches, write to the Person object
            if (rs.next()) {
                person = new Person();
                person.setName(rs.getString("name"));
                person.setSurame(rs.getString("surname"));
                person.setBirthDate(rs.getString("birthDate"));
                person.setEmail(rs.getString("email"));
                person.setTelephone(rs.getString("telephone"));
            }
	    // Close 
            rs.close();
            ps.close();
	    // return person
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
    
    /**
     * This functions return all the Person objects in the database.
     * <p>
     * This method receives the Id of a user and fetches the corresponding
     * user from the database, returning a Person object.
     * @return an ArrayList<Person> of all the persons in the database.
     */
    @Override
    public ArrayList<Person> getAllPersons(){
        // The SQL code to be sent
        String sql = "SELECT * FROM PERSON";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);		    // <-- Change this 
	    // Creates an ArrayList to return, this ArrayList contains all the Person rows that exist
            ArrayList<Person> resultsArrayList = new ArrayList<Person>();
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // While we have more persons that match, write them the Person object
            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("name"));
                person.setSurame(rs.getString("surname"));
                person.setBirthDate(rs.getString("birthDate"));
                person.setEmail(rs.getString("email"));
                person.setTelephone(rs.getString("telephone"));
		// Add the person object to the returned ArrayList
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
