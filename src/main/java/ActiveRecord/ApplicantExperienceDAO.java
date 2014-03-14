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
 
public interface ApplicantExperienceDAO {
    public void insert(ApplicantExperience applicantExperience) throws Exception;
    public ArrayList<ApplicantExperience> getAllApplicantIDExpertises(int applicantID) throws Exception;
    public ArrayList<ApplicantExperience> getExpertiseWhere(String insertedSQL) throws Exception;
}