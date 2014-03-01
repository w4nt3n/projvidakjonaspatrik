/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;

/**
 *
 * @author Vidak
 */
public class Expertise {
    
    int ID;
    String expertise;
    
    //getter and setter methods
    
    //getters
    public int getID() {
        return ID;
    }
    public String getExpertiseName() {
        return expertise;
    }
    
    //setters
    public void setID (int ID) {
        this.ID = ID;
    }
    public void setExpertiese(String exp) {
        this.expertise = exp;
    }
}
