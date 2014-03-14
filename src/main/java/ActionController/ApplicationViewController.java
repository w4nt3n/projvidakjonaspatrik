/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.Applicant;
import ActiveRecord.ApplicantAvailability;
import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicationViewBean;
import ActiveRecord.Expertise;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView showAppliers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
	// Get the value of a request parameter; the name is case-sensitive
	String name = "applicantID";
	int targetApplicantID;
	String parameter = "";
	if(req.getParameter(name) == null)
	    try (PrintWriter out = response.getWriter()) {
		out.println("Error, you didnt submit any user");
	    }
	
	parameter = req.getParameter(name);
	
	targetApplicantID = Integer.parseInt(parameter);
	// Creates a bean for MySQL ApplicantDAO object.
	// Spring-Module.xml contains references to Spring-Datasource.xml
	// and Spring-Applicant.xml, these contain beans for Applicant and database login
	
	// Creates a new applicant and sets all of it's values
	ApplicationViewBean appBean = new ApplicationViewBean();
	
	// This is used for all access to Applicant class in the database
	ApplicationDataSourceManager appDSM = new ApplicationDataSourceManager();
	ArrayList<Applicant> appList = appDSM.getApplicantIDWhere("id='" + targetApplicantID + "'");
	
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
		}
	    }
	}
	
	ApplicationAvailabilityDataSourceManager appAvDSM = new ApplicationAvailabilityDataSourceManager();
	ArrayList<ApplicantAvailability> appAvList = appAvDSM.getAllApplicantAvailability(appList.get(0).getId());
	for(int i = 0; i < appAvList.size(); i++)
	    appBean.setAddToAvList(appAvList.get(i));
	
	// Just returns application jsp
        return new ModelAndView("applicationview", "message", appBean);
    }
}