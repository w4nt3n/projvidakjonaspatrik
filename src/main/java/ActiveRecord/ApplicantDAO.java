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
    public void insert(Applicant applicant);
    public Applicant findById(int applicantId);
    public ArrayList<Applicant> getAllApplicants();
}