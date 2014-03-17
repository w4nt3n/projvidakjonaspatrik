/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicantAvailability;
import ActiveRecord.ApplicantAvailabilityDAO;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vidak
 */
public class ApplicationAvailabilityDataSourceManager {
    private JdbcApplicantDAO jdbcAppAvDAO;
    private final ApplicantAvailabilityDAO appAvDAO;
    private final ApplicationContext context;
    
    public ApplicationAvailabilityDataSourceManager(){
	this.context = new ClassPathXmlApplicationContext("Spring-ApplicantAvailabilityModule.xml");
	// Fetches the applicantDAO bean
        this.appAvDAO = (ApplicantAvailabilityDAO) this.context.getBean("ApplicantAvailabilityDAO");
    }
    
    public void insert(ApplicantAvailability applicantAvailability){
	this.appAvDAO.insert(applicantAvailability);
    }
    
//    public void insert(ArrayList<ApplicantAvailability> applicantAvailabilityList){
//	this.appAvDAO.insert(applicantAvailabilityList);
//    }
    
    public ArrayList<ApplicantAvailability> getAllApplicantAvailability(long applicantID){
	return this.appAvDAO.getAllApplicantAvailability(applicantID);
    }
    
}
