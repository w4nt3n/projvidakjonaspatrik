/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Applicant;
import ActiveRecord.ApplicantDAO;

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

public class JdbcApplicantDAO implements ApplicantDAO {
    private DataSource dataSource;
 
    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    /**
     * This function writes a Applicant to the database. 
     * <p>
     * This method receives a Applicant and writes it to the database
     * @param applicant The Applicant object to be written
     */
    @Override
    public void insert(Applicant applicant){

	// The SQL code to be sent
        String sql = "INSERT INTO applicant " +
                        "(id, name, surname, dateOfBirth, email, telephone) VALUES (?, ?, ?, ?, ?, ?)";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, applicant.geId());
            ps.setString(2, applicant.getName());
            ps.setString(3, applicant.getSurname());
            ps.setString(4, applicant.getDateOfBirth());
            ps.setString(5, applicant.getEmail());
            ps.setString(6, applicant.getTelephone());
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
     * This function searches for a applicant with the specific Id and
     * returns a Applicant object if found.
     * <p>
     * This method receives the Id of a user and fetches the corresponding
     * user from the database, returning a Applicant object.
     * @param applicantId The applicant Id to be fetched
     * @return A Applicant object.
     */
    @Override
    public Applicant findById(int applicantId){

	// The SQL code to be sent
        String sql = "SELECT * FROM applicant WHERE id = ?";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, applicantId);
	    // Creates a applicant to return
            Applicant applicant = null;
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            if (rs.next()) {
                applicant = new Applicant();
                applicant.setName(rs.getString("name"));
                applicant.setSurame(rs.getString("surname"));
                applicant.setDateOfBirth(rs.getString("dateOfBirth"));
                applicant.setEmail(rs.getString("email"));
                applicant.setTelephone(rs.getString("telephone"));
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
            return applicant;
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
     * This functions return all the Applicant objects in the database.
     * <p>
     * This method receives the Id of a user and fetches the corresponding
     * user from the database, returning a Applicant object.
     * @return an ArrayList<Applicant> of all the applicants in the database.
     */
    @Override
    public ArrayList<Applicant> getAllApplicants(){
        // The SQL code to be sent
        String sql = "SELECT * FROM applicant";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);		    // <-- Change this 
	    // Creates an ArrayList to return, this ArrayList contains all the Applicant rows that exist
            ArrayList<Applicant> resultsArrayList = new ArrayList<Applicant>();
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // While we have more applicants that match, write them the Applicant object
            while (rs.next()) {
                Applicant applicants = new Applicant();
                applicants.setName(rs.getString("name"));
                applicants.setSurame(rs.getString("surname"));
                applicants.setDateOfBirth(rs.getString("dateOfBirth"));
                applicants.setEmail(rs.getString("email"));
                applicants.setTelephone(rs.getString("telephone"));
		// Add the applicant object to the returned ArrayList
                resultsArrayList.add(applicants);
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
