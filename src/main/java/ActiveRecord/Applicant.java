package ActiveRecord;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vidak
 */

public class Applicant
{
    public Applicant() {
    }
    
    long id;
    String firstname;
    String lastname;
    String dateOfBirth;
    String email;
    String phone;
    
    
    //getter and setter methods
    
    //getters
    public long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    
    //setters
    public void setId(long id) {
        this.id = id;
    }
    public void setFirstname(String n) {
        this.firstname = n;
    }
    public void setLastname(String n) {
        this.lastname = n;
    }
    public void setDateOfBirth(String bd) {
        this.dateOfBirth = bd;
    }
    public void setEmail(String e) {
        this.email = e;
    }
    public void setPhone(String t) {
        this.phone = t;
    }
}
