/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ActiveRecord;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vidak
 */
 
public interface ApplicantAvailabilityDAO  {
    public void insert(ApplicantAvailability applicantAvailability) throws SQLException;
    public ArrayList<ApplicantAvailability> getAllApplicantAvailability(long applicantID) throws SQLException;
}