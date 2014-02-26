package ActiveRecord;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ActiveRecord.ApplicationBean;
 
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Vidak
 */


import java.sql.Timestamp;
 
public class Person
{
    public Person(String n, String sn, String em, String tel)
    {
        this.name = n;
        this.surname = sn;
        this.email = em;
        this.telephone = tel;
    }
    
    int personId;
    String name;
    String surname;
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
    public void setEmail(String e) {
        this.email = e;
    }
    public void setTelephone(String t) {
        this.telephone = t;
    }
}
