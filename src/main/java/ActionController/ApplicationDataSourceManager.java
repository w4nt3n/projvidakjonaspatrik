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
 * @author Vidak, Patrik, Jonas
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
    
    /**
     * Writes an applicant to the DB
     * @param applicant
     * @return
     * @throws Exception 
     */
    public long insert(Applicant applicant) throws Exception{
        long id;
	try{
	    id = appDAO.insert(applicant);
	} catch(Exception e){
	    throw new Exception(e);
	}
        return id;
    }
    
    /**
     * Gets all applications from the DB
     * @return
     * @throws Exception 
     */
    public ArrayList<Applicant> getAllApplicants() throws Exception{
	try{
	    return this.appDAO.getAllApplicants();
	} catch(Exception e){
	    throw new Exception(e);
	}
    }
    
    /**
     * Gets all applications where conditions are met from DB
     * @param insertedSQL
     * @return
     * @throws Exception 
     */
    public ArrayList<Applicant> getApplicantWhere(String insertedSQL) throws Exception{
	try{
	    return this.appDAO.getApplicantIDWhere(insertedSQL);
	} catch(Exception e){
	    throw new Exception(e);
	}
    }

    /**
     * Gets all applications where the applicant can work inbetween two dates.
     * @param datepickerFrom
     * @param datepickerTo
     * @return
     * @throws Exception 
     */
    ArrayList<Applicant> getApplicantAvailable(String datepickerFrom, String datepickerTo) throws Exception {
        return this.appDAO.getApplicantAvailable(datepickerFrom, datepickerTo);
    }
}
