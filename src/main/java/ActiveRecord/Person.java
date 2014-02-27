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

public class Person
{
    public Person() {
    }
    
    int personId;
    String name;
    String surname;
    String birthDate;
    String email;
    String telephone;
    
    
    //getter and setter methods
    
    //getters
    public int getPersonId() {
        return personId;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    
    //setters
    public void setPersonId(int id) {
        this.personId = id;
    }
    public void setName(String n) {
        this.name = n;
    }
    public void setSurame(String n) {
        this.surname = n;
    }
    public void setBirthDate(String bd) {
        this.birthDate = bd;
    }
    public void setEmail(String e) {
        this.email = e;
    }
    public void setTelephone(String t) {
        this.telephone = t;
    }
}
