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
 
public interface ApplicantDAO 
{
    public long insert(Applicant applicant) throws Exception;
    public Applicant getApplicantWithId(int applicantId) throws Exception;
    public ArrayList<Applicant> getAllApplicants() throws Exception;
    public ArrayList<Applicant> getApplicantIDWhere(String insertedSQL) throws Exception;

    public ArrayList<Applicant> getApplicantAvailable(String datepickerFrom, String datepickerTo) throws Exception;
}