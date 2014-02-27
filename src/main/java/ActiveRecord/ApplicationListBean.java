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

public class ApplicationListBean {
    
    private ArrayList<Person> allApplications;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public ApplicationListBean()
    {
        this.allApplications = new ArrayList<Person>();
    }
    
    //getters
    public ArrayList<Person> getAllApplications() {
        return allApplications;
    }

    //setters
    public void setAllApplications(ArrayList<Person> list) {
        this.allApplications = list;
    }
    
    public void addPerson(Person person)
    {
        this.allApplications.add(person);
    }
}
