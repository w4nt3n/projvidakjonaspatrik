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
public class ApplicantExperience {
    
    String expertise;
    int years;
    
    //getter and setter methods
    
    //getters
    public String geExpertiese() {
        return expertise;
    }
    public int getYears() {
        return years;
    }
    
    //setters
    public void setExpertiese(String exp) {
        this.expertise = exp;
    }
    public void setYears(int y) {
        this.years = y;
    }
}
