/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

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

public class MySQLHandler {
    /*private DataSource dataSource;
    private Connection conn;
    private ResultSet resultset;

    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }
    
    SQL sql = new SQL();
    SQL.select("id="+string(4), "expertiseList", "id", "expertise");
    while(SQL.getRow()) {
        add(SQL.getColumn("id"));
    }

    public boolean select(String conditions, String table, String...columnNames){
	// If any columns have been provided, join them into a comma separated string,
        // otherwise, use "*" for all columns in table.
        String columns = "";
        if(columnNames.length == 0) {
            columns = "*";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(columnNames[0]);
            for(int i = 1; i < columnNames.length; i++)
                sb.append(",").append(columnNames[i]);
            columns = sb.toString();
        }
        
        // Setup the query
        String sql = "SELECT "+columns+" FROM "+table+" WHERE "+conditions;
        
        try {
	    // Get a connection (the source is Spring-Datasource.xml)
            conn = dataSource.getConnection();
	    // We have paramaters, we need a prepareStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            // Run query
            ResultSet rs = ps.executeQuery();
            
	    // If we get a applicant that matches, write to the Applicant object
            if (rs.next()) {
                exp = new Expertise();
                exp.setID(rs.getInt("id"));
                exp.setExpertise(rs.getString("expertise"));
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
		exp.setExpertise(rs.getString("expertise"));
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
    }*/
}
