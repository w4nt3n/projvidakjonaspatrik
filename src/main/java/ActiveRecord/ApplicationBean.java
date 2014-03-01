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
    private String name;
    private String surname;
    private int age;
    private String email;
    private String telephone;
    
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
    public String getTelephone() {
        return telephone;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge(){
        return age;
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
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setName(String firstname) {
        this.name = firstname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setExpertiseList(ArrayList<Expertise> expList){
        this.expertiseList = expList;
    }
}
