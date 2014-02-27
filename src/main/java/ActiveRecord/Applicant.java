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
    
    int id;
    String name;
    String surname;
    String dateOfBirth;
    String email;
    String telephone;
    
    
    //getter and setter methods
    
    //getters
    public int geId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    
    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String n) {
        this.name = n;
    }
    public void setSurame(String n) {
        this.surname = n;
    }
    public void setDateOfBirth(String bd) {
        this.dateOfBirth = bd;
    }
    public void setEmail(String e) {
        this.email = e;
    }
    public void setTelephone(String t) {
        this.telephone = t;
    }
}
