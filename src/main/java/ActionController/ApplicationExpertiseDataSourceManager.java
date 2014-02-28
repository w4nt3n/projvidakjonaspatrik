/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Expertise;
import ActiveRecord.ExpertiseDAO;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vidak
 */
public class ApplicationExpertiseDataSourceManager {
    private ExpertiseDAO appExpertiseDAO;
    private ApplicationContext context;
    
    public ApplicationExpertiseDataSourceManager(){
	this.context = new ClassPathXmlApplicationContext("Spring-ApplicantExpertiseModule.xml");
	// Fetches the applicantDAO bean
        this.appExpertiseDAO = (ExpertiseDAO) this.context.getBean("expertiseDAO");
    }
    
    
    public Expertise getExpertiseWithId(int ID){
	return this.appExpertiseDAO.getExpertiseWithId(ID);
    }
    
    public int getIdWithExpertise(String expertise){
	return this.appExpertiseDAO.getIdWithExpertise(expertise);
    }
    
    public ArrayList<Expertise> getAllExpertises(){
	return this.appExpertiseDAO.getAllExpertises();
    }
}
