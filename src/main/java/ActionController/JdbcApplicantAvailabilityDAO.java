/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicantAvailability;
import ActiveRecord.ApplicantAvailabilityDAO;

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

public class JdbcApplicantAvailabilityDAO implements ApplicantAvailabilityDAO {
    private DataSource dataSource;
 
    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    @Override
    public void insert(ApplicantAvailability applicantAvailability) throws SQLException {

	// The SQL code to be sent
        String sql = "INSERT INTO applicantavailability (applicantID, dateFrom, dateTo) VALUES (?, ?, ?)";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,   applicantAvailability.getApplicantID());
            ps.setString(2, applicantAvailability.getFrom());
            ps.setString(3, applicantAvailability.getTo());
	    // Execute query
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new SQLException("Creating availability failed, could not connect to database.");
        } finally {
            if (conn != null) {
                try {
                        conn.close();
                } catch (SQLException e) {}
            }
        }
    }
 
    @Override
    public ArrayList<ApplicantAvailability> getAllApplicantAvailability(long applicantID) throws SQLException{
	// The SQL code to be sent
        String sql = "SELECT * FROM applicantavailability WHERE applicantID = ?";
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
	    ArrayList<ApplicantAvailability> resultsArrayList = new ArrayList<>();
	    ps.setLong(1, applicantID);
	    // Creates a applicant to return
            ApplicantAvailability appExp = null;
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            while (rs.next()) {
                appExp = new ApplicantAvailability();
                appExp.setApplicantID(rs.getInt("applicantID"));
                appExp.setFrom(rs.getString("dateFrom"));
                appExp.setTo(rs.getString("dateTo"));
		resultsArrayList.add(appExp);
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
            return resultsArrayList;
        } catch (SQLException e) {
            throw new SQLException("Loading periods of availability failed, no connection.");
        } finally {
            if (conn != null) {
                try {
                conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
