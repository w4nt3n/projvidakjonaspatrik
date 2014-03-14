/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ActiveRecord;

import java.util.ArrayList;

/**
 *
 * @author Vidak
 */
 
public interface ApplicantAvailabilityDAO {
    public void insert(ApplicantAvailability applicantAvailability);
    //public void insert(ArrayList<ApplicantAvailability> applicantAvailabilityList);
    public ArrayList<ApplicantAvailability> getAllApplicantAvailability(int applicantID);
}