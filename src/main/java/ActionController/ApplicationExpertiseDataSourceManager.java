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
    private final ExpertiseDAO appExpertiseDAO;
    private final ApplicationContext context;
    
    public ApplicationExpertiseDataSourceManager(){
	this.context = new ClassPathXmlApplicationContext("Spring-ApplicantExpertiseModule.xml");
	// Fetches the applicantDAO bean
        this.appExpertiseDAO = (ExpertiseDAO) this.context.getBean("expertiseDAO");
    }
    
    
    public Expertise getExpertiseWithId(int ID) throws Exception{
	try{
	    return this.appExpertiseDAO.getExpertiseWithId(ID);
	} catch (Exception e){
	    throw new Exception(e);
	}
    }
    
    public int getIdWithExpertise(String expertise) throws Exception{
	try{
	    return this.appExpertiseDAO.getIdWithExpertise(expertise);
	} catch (Exception e){
	    throw new Exception(e);
	}
    }
    
    public ArrayList<Expertise> getAllExpertises() throws Exception{
	try{
	    return this.appExpertiseDAO.getAllExpertises();
	} catch (Exception e){
	    throw new Exception(e);
	}
    }
}
