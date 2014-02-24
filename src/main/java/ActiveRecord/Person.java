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
    public Person(String n, int a)
    {
        this.name = n;
        this.age = a;
    }
    
    int custId;
    String name;
    int age;
    //getter and setter methods
    
    //getters
    public int getCustId() {
        return custId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }

    //setters
    public void setCustId(int id) {
        this.custId = id;
    }
    
    public void setName(String n) {
        this.name = n;
    }
    
    public void setAge(int a) {
        this.age = a;
    }
}
