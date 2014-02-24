/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;


/**
 *
 * @author Jonas
 */

public class ApplicationBean {

    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //getters
    public String getEmail() {
            return email;
    }
    public String getTelephone() {
            return telephone;
    }
    public String getFirstname() {
            return firstname;
    }
    public String getLastname() {
            return lastname;
    }

    //setters
    public void setEmail(String email) {
            this.email = email;
    }
    public void setTelephone(String telephone) {
            this.telephone = telephone;
    }
    public void setFirstname(String firstname) {
            this.firstname = firstname;
    }
    public void setLastname(String lastname) {
            this.lastname = lastname;
    }
}
