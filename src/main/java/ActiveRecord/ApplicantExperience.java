/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;

/**
 *
 * @author Vidak
 */
public class ApplicantExperience {
    
    long applicantID;
    int expertise;
    int years;
    
    //getter and setter methods
    
    //getters
    public long getApplicantID() {
        return applicantID;
    }
    public int getExpertise() {
        return expertise;
    }
    public int getYears() {
        return years;
    }
    
    //setters
    public void setApplicantID(long ID) {
        this.applicantID = ID;
    }
    public void setExpertise(int exp) {
        this.expertise = exp;
    }
    public void setYears(int y) {
        this.years = y;
    }
}
