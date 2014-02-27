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
    
    private ArrayList<Applicant> allApplications;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public ApplicationListBean(){
        this.allApplications = new ArrayList<Applicant>();
    }
    
    //getters
    public ArrayList<Applicant> getAllApplications() {
        return allApplications;
    }

    //setters
    public void setAllApplications(ArrayList<Applicant> list) {
        this.allApplications = list;
    }
    
    public void addPerson(Applicant applicant){
        this.allApplications.add(applicant);
    }
}
