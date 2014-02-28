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
    private ApplicantDAO appDAO;
    private ApplicationContext context;
    
    public ApplicationDataSourceManager(){
	this.context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	// Fetches the applicantDAO bean
        this.appDAO = (ApplicantDAO) this.context.getBean("applicantDAO");
    }
    
    public void insert(Applicant applicant){
	this.appDAO.insert(applicant);
    }
    
    public ArrayList<Applicant> getAllApplicants(){
	return this.appDAO.getAllApplicants();
    }
    
    public ArrayList<Applicant> getApplicantIDWhere(String insertedSQL){
	return this.appDAO.getApplicantIDWhere(insertedSQL);
    }
}
