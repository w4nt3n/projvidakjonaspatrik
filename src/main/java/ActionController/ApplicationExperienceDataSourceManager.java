/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicantExperienceDAO;
import ActiveRecord.Expertise;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vidak
 */
public class ApplicationExperienceDataSourceManager {
    private JdbcApplicantDAO jdbcAppDAO;
    private ApplicantExperienceDAO appExpDAO;
    private ApplicationContext context;
    
    public ApplicationExperienceDataSourceManager(){
	this.context = new ClassPathXmlApplicationContext("Spring-ApplicantExperienceModule.xml");
	// Fetches the applicantDAO bean
        this.appExpDAO = (ApplicantExperienceDAO) this.context.getBean("ApplicantExperienceDAO");
    }
    
    public void insert(ApplicantExperience applicantExperience)throws Exception{
	try{
	    this.appExpDAO.insert(applicantExperience);
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
    
    public ArrayList<ApplicantExperience> getAllApplicantIDExpertises(int applicantID) throws Exception{
	try{
	    return this.appExpDAO.getAllApplicantIDExpertises(applicantID);
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
    
    public ArrayList<ApplicantExperience> getExpertiseWhere(String insertedSQL) throws Exception {
	try{
	    return this.appExpDAO.getExpertiseWhere(insertedSQL);
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
}
