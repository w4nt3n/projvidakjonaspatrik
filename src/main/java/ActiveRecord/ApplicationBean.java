/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;

import java.util.ArrayList;


/**
 *
 * @author Jonas
 */

public class ApplicationBean {

    private boolean afterSubmit;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    private ArrayList<Expertise> expertiseList;
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
    public ArrayList<Expertise> getExpertiseList(){
        return expertiseList;
    }

    //setters
    public void setAfterSubmit(boolean afterSubmit){
        this.afterSubmit = afterSubmit;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String telephone) {
        this.phone = telephone;
    }
    public void setFirstname(String name) {
        this.firstname = name;
    }
    public void setLastname(String surname) {
        this.lastname = surname;
    }
    public void setExpertiseList(ArrayList<Expertise> expList){
        this.expertiseList = expList;
    }
}
