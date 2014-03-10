/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Applicant;
import ActiveRecord.ApplicantDAO;
import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicantExperienceDAO;
import ActiveRecord.Expertise;

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

public class JdbcApplicantExperienceDAO implements ApplicantExperienceDAO {
    private DataSource dataSource;
 
    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    @Override
    public void insert(ApplicantExperience applicantExperience){

	// The SQL code to be sent
        String sql = "INSERT INTO applicantexperience " +
                        "(applicantID, expertiseID, yearsOfExperience) VALUES (?, ?, ?)";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, applicantExperience.getApplicantID());
            ps.setInt(2, applicantExperience.getExpertise());
            ps.setInt(3, applicantExperience.getYears());
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

    @Override
    public ArrayList<ApplicantExperience> getAllApplicantIDExpertises(int applicantID){

	// The SQL code to be sent
        String sql = "SELECT * FROM applicantexperience WHERE applicantID = ?";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, applicantID);
	    ArrayList<ApplicantExperience> resultsArrayList = new ArrayList<ApplicantExperience>();	    // <----- comment
	    // Creates a applicant to return
            ApplicantExperience appExp = null;
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            if (rs.next()) {
                appExp = new ApplicantExperience();
                appExp.setApplicantID(rs.getInt("applicantID"));
                appExp.setExpertise(rs.getInt("expertiseID"));
                appExp.setYears(rs.getInt("dateOfBirth"));
		resultsArrayList.add(appExp);
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
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
    
    @Override
    public ArrayList<ApplicantExperience> getExpertiseWhere(String insertedSQL) {
	// The SQL code to be sent
        String sql = "SELECT * FROM applicantexperience WHERE " + insertedSQL;
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
	    ArrayList<ApplicantExperience> resultsArrayList = new ArrayList<ApplicantExperience>();
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            while (rs.next()) {
		ApplicantExperience exp = new ApplicantExperience();
		exp.setApplicantID(rs.getInt("expertiseID"));
		exp.setExpertise(rs.getInt("expertiseID"));
		exp.setYears(rs.getInt("yearsOfExperience"));
		// Add the applicant object to the returned ArrayList
                resultsArrayList.add(exp);
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
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
