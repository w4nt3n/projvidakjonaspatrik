/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Applicant;
import ActiveRecord.ApplicantDAO;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vidak
 */
public class ApplicationDataSourceManager {
    private JdbcApplicantDAO jdbcAppDAO;
    private final ApplicantDAO appDAO;
    private final ApplicationContext context;
    
    public ApplicationDataSourceManager(){
	
	this.context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	// Fetches the applicantDAO bean
	this.appDAO = (ApplicantDAO) this.context.getBean("applicantDAO");
    }
    
    public long insert(Applicant applicant) throws Exception{
        long id;
	try{
	    id = appDAO.insert(applicant);
	} catch(Exception e){
	    throw new Exception(e);
	}
        return id;
    }
    
    public ArrayList<Applicant> getAllApplicants() throws Exception{
	try{
	    return this.appDAO.getAllApplicants();
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
    
    public ArrayList<Applicant> getApplicantIDWhere(String insertedSQL) throws Exception{
	try{
	    return this.appDAO.getApplicantIDWhere(insertedSQL);
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
}
