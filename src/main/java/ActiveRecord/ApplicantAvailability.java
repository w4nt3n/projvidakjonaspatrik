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
public class ApplicantAvailability {
    
    long applicantID;
    String from;
    String to;
    
    //getter and setter methods
    
    //getters
    public long getApplicantID() {
        return applicantID;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    
    //setters
    public void setApplicantID (long ID) {
        this.applicantID = ID;
    }
    public void setFrom(String fro) {
        this.from = fro;
    }
    public void setTo(String to) {
        this.to = to;
    }
}
