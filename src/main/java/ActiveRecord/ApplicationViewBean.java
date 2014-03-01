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
    private String name;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String telephone;
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
    public String getTelephone() {
        return telephone;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
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
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setName(String firstname) {
        this.name = firstname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddToExpExpList(Expertise expertise, ApplicantExperience applicantExperience){
        this.expExpList.add(new ExpertiseExperienceContainer(expertise, applicantExperience));
    }
}
