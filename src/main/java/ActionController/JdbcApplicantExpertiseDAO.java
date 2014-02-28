/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicantExperienceDAO;
import ActiveRecord.Expertise;
import ActiveRecord.ExpertiseDAO;

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

public class JdbcApplicantExpertiseDAO implements ExpertiseDAO {
    private DataSource dataSource;
 
    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    @Override
    public Expertise getExpertiseWithId(int ID){
	// The SQL code to be sent
        String sql = "SELECT * FROM expertiselist WHERE id = ?";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
	    Expertise exp = new Expertise();	    // <----- comment
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            if (rs.next()) {
                exp = new Expertise();
                exp.setID(rs.getInt("id"));
                exp.setExpertiese(rs.getString("expertise"));
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
            return exp;
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
    public int getIdWithExpertise(String expertise) {
	// The SQL code to be sent
        String sql = "SELECT * FROM expertiselist WHERE expertise = ?";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, expertise);
	    Expertise exp = new Expertise();	    // <----- comment
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            if (rs.next()) {
                exp = new Expertise();
                exp.setID(rs.getInt("id"));
                exp.setExpertiese(rs.getString("expertise"));
            }
	    // Close 
            rs.close();
            ps.close();
	    // return applicant
            return exp.getID();
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
    public ArrayList<Expertise> getAllExpertises() {
	// The SQL code to be sent
        String sql = "SELECT * FROM expertiselist";
	// The object containing the connection
        Connection conn = null;

        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
	    ArrayList<Expertise> resultsArrayList = new ArrayList<Expertise>();	    // <----- comment
	    // Creates a applicant to return
            Expertise exp = null;
	    // Execute query
            ResultSet rs = ps.executeQuery();
	    // If we get a applicant that matches, write to the Applicant object
            while (rs.next()) {
                exp = new Expertise();
                exp.setID(rs.getInt("id"));
		exp.setExpertiese(rs.getString("expertise"));
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
