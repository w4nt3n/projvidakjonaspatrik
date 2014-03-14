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

public class ApplicationViewBean {
    
    private boolean afterSubmit;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String email;
    private String phone;
    public ArrayList<ExpertiseExperienceContainer> expExpList;
    
    public ApplicationViewBean(){
	this.expExpList = new ArrayList<ExpertiseExperienceContainer>();
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //getters
    public boolean getAfterSubmit(){
        return this.afterSubmit;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public ArrayList<ExpertiseExperienceContainer> getExpExpList(){
        return expExpList;
    }

    //setters
    public void setAfterSubmit(boolean afterSubmit){
        this.afterSubmit = afterSubmit;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddToExpExpList(Expertise expertise, ApplicantExperience applicantExperience){
        this.expExpList.add(new ExpertiseExperienceContainer(expertise, applicantExperience));
    }
}