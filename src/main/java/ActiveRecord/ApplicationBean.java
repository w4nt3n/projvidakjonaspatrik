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

public class ApplicationBean implements Validatable {

    private boolean hasError = false;
    private boolean afterSubmit;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String error;

    private ArrayList<Expertise> expertiseList;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //getters
    public boolean hasError(){
	return this.hasError;
    }
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
    public String getError() {
        return error;
    }
    public ArrayList<Expertise> getExpertiseList(){
        return expertiseList;
    }

    //setters
    public void hasError(boolean value){
	this.hasError = value;
        setError("");
    }
    public void hasError(boolean value, String error){
	this.hasError = value;
        setError(error);
    }
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
    public void setError(String error) {
        this.error = error;
    }
    public void setExpertiseList(ArrayList<Expertise> expList){
        this.expertiseList = expList;
    }
    
    @Override
    public boolean Validate() {
        return (firstname.length() > 0 &&
                lastname.length() > 0 &&
                phone.matches("^\\+?[0-9\\-\\s]{7,}$") &&
                email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"));
    }
}