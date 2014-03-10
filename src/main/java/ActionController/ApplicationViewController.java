/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Applicant;
import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicationBean;
import ActiveRecord.ApplicationViewBean;
import ActiveRecord.Expertise;
import ActiveRecord.ExpertiseDAO;
import java.util.ArrayList;
import javax.faces.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vidak
 */

@Controller
@SessionAttributes
public class ApplicationViewController {
 
    @RequestMapping("/applicationView")
    public ModelAndView showAppliers() {
	
	// Creates a bean for MySQL ApplicantDAO object.
	// Spring-Module.xml contains references to Spring-Datasource.xml
	// and Spring-Applicant.xml, these contain beans for Applicant and database login
	
	// Creates a new applicant and sets all of it's values
	ApplicationViewBean appBean = new ApplicationViewBean();
	
	// This is used for all access to Applicant class in the database
	ApplicationDataSourceManager appDSM = new ApplicationDataSourceManager();
	ArrayList<Applicant> appList = appDSM.getApplicantIDWhere("name='newNamedvdvdsdvds' AND surname='NewSurnamesdvdsv'");
	
	if(appList.size() >= 1){
	    appBean.setFirstname(appList.get(0).getFirstname());
	    appBean.setLastname(appList.get(0).getLastname());
	    appBean.setDateOfBirth(appList.get(0).getDateOfBirth());
	    appBean.setEmail(appList.get(0).getEmail());
	    appBean.setPhone(appList.get(0).getPhone());

	    // This is used for all access to Applicant class in the database
	    ApplicationExperienceDataSourceManager appExperienceDSM = new ApplicationExperienceDataSourceManager();
	    // Gets the id of all the 
	    ArrayList<ApplicantExperience> appExperienceList = appExperienceDSM.getExpertiseWhere("applicantID='" + appList.get(0).getId() + "'");
	    if(appExperienceList.size() >= 1){
		// This is used for all access to Expertise class in the database
		ApplicationExpertiseDataSourceManager appExpertiseDSM = new ApplicationExpertiseDataSourceManager();
		
		for(int i = 0; i < appExperienceList.size(); i++){
		    Expertise expertise = new Expertise();
		    expertise.setExpertise(appExpertiseDSM.getExpertiseWithId(appExperienceList.get(i).getExpertise()).getExpertiseName());
		    appBean.setAddToExpExpList(expertise, appExperienceList.get(i));
		    appBean.setFirstname(appBean.getFirstname() + appBean.getExpExpList().get(i).getExpertise().getExpertiseName());
		}
	    }
	}
	
	// Just returns application jsp
        return new ModelAndView("applicationview", "message", appBean);
    }
}