/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;

import ActionController.ApplicationDataSourceManager;
import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vidak
 */

public class ApplicationListBean {
    
    private boolean hasError = false;
    private String error;
    
    private ArrayList<Applicant> allApplications;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public ApplicationListBean(){
        this.allApplications = new ArrayList<Applicant>();
    }
    
    //getters
    public boolean hasError(){
	return this.hasError;
    }
    public String getError(){
        return this.error;
    }
    public ArrayList<Applicant> getAllApplications() {
        return allApplications;
    }

    //setters
    public void hasError(boolean value){
	this.hasError = value;
        this.error = "";
    }
    public void hasError(boolean value, String error){
        this.error = error;
	this.hasError = value;
    }
    public void setAllApplications(ArrayList<Applicant> list) {
        this.allApplications = list;
    }
    public void addPerson(Applicant applicant){
        this.allApplications.add(applicant);
    }
}
