/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

/**
 *
 * @author Jonas, Vidak
 */
 
import ActiveRecord.ApplicationBean;
import ActiveRecord.Applicant;
import ActiveRecord.ApplicantDAO;
import ActiveRecord.ApplicantExperience;
import ActiveRecord.ApplicantExperienceDAO;
import ActiveRecord.ExpertiseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
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
 
@Controller
@SessionAttributes
public class ApplicationController {
 
    /**
     * The method called when the form is submitted. All data collection 
     * and writing is done here.
     * <p>
     * This method collects the form values inputed by the user and writes
     * them to the database, whereafter the user sees a message stating that
     * the write was completed.
     * @param application
     * @param result
     * @param birthdayYearSelect
     * @param birthdayMonthSelect
     * @param birthdayDaySelect
     * @return a ModelAndView of the application.jsp
     */
    @RequestMapping(value = "/addApplier", method = RequestMethod.POST)
    public ModelAndView addApplier(@ModelAttribute("application")
                            ApplicationBean application, BindingResult result, 
                            @RequestParam("birthdayYearSelect") String birthdayYearSelect,
                            @RequestParam("birthdayMonthSelect") String birthdayMonthSelect,
                            @RequestParam("birthdayDaySelect") String birthdayDaySelect,
			    @RequestParam("workExpertiseInput") String workExpertiseInput){
        
	ArrayList<ApplicantExperience> appExpList = new ArrayList<ApplicantExperience>();
	
	// This is used for all access to Expertise class in the database
	ApplicationExpertiseDataSourceManager appExpertiseDSM = new ApplicationExpertiseDataSourceManager();
	
	//------------------------------------------------------------
	//----------------------- Parses all of the expertises -------
	
	String stringBuffer = "";
	int mode = 1;
	ApplicantExperience appExp = new ApplicantExperience();
	for (int i = 0; i < workExpertiseInput.length(); i++){	// Goes through all of the characters
	    if (workExpertiseInput.charAt(i) == ','){	// If the next character is a tab
		if (stringBuffer.length() >= 1){	// and we currently have something in the buffer
		    if (mode == 1){			// and we are searching for an expertise
			appExp.setExpertiese(appExpertiseDSM.getIdWithExpertise(stringBuffer)); // set the expertise in the current applicantExpertise
			stringBuffer = "";		    // clear the buffer
			mode = 2;			    // start search for years
		    }
		    else if (mode == 2){			    // if searching for years
			appExp.setYears(Integer.parseInt(stringBuffer));    // set the years in the current applicantExpertise
			stringBuffer = "";				    // clear the buffer
			mode = 1;					    // start search for expertise			    
			appExpList.add(appExp);					    // add expertise to expertise list
			appExp = new ApplicantExperience();		    // create a new expertise
		    }
		}
	    }
	    else
		stringBuffer = stringBuffer + workExpertiseInput.charAt(i);
	}
	
	//------------------------------------------------------------
	//----------------------- Writes to the applicant ------------
	
	// This is used for all access to Applicant class in the database
	ApplicationDataSourceManager appDSM = new ApplicationDataSourceManager();
	
	// Creates a new applicant and sets all of it's values
        Applicant applicant = new Applicant();
        applicant.setName(application.getName());
        applicant.setSurame(application.getSurname());
        String sqlDateFormat = birthdayYearSelect + "-" + birthdayMonthSelect + "-" + birthdayDaySelect;
        applicant.setDateOfBirth(sqlDateFormat);
        applicant.setEmail(application.getEmail());
        applicant.setTelephone(application.getTelephone());
        
	// Adds the new applicant to the database
        appDSM.insert(applicant);
	
	
	//------------------------------------------------------------
	//----------------------- Writes to the experience ------------
	
	if(appExpList.size() >= 1){
	    
	    // This is used for all access to Experience class in the database
	    ApplicationExperienceDataSourceManager appExperienceDSM = new ApplicationExperienceDataSourceManager();
	    
	    // Creates a new applicant and sets all of it's values
	    ApplicantExperience applicantExperience = null;
	    // Get the ID of the newly added appicant
	    ArrayList<Applicant> appThatMatch = null;
	    
	    for(int i = 0; i < appExpList.size(); i++){
		// Creates a new applicant and sets all of it's values
		applicantExperience = new ApplicantExperience();
		// Get the ID of the newly added appicant
		appThatMatch = appDSM.getApplicantIDWhere("name='" + applicant.getName() + "' AND"
			+ " surname='" + applicant.getSurname() + "' AND"
			+ " dateOfBirth='" + applicant.getDateOfBirth() + "' AND"
			+ " email='" + applicant.getEmail() + "' AND"
			+ " telephone='" + applicant.getTelephone() + "'");
		
		if(appThatMatch.size() == 1){
		    // There should only be one that matches, lets make an error message if there are more than one later
		    applicantExperience.setApplicantID(appThatMatch.get(0).getId());
		    applicantExperience.setExpertiese(appExpList.get(i).getExpertiese());
		    applicantExperience.setYears(appExpList.get(i).getYears());

		    // Adds the new applicant to the database
		    appExperienceDSM.insert(applicantExperience);
		}
		else{
		    // Error there are multiple applicants that are identical
		}
	    }
	}
	//------------------------------------------------------------
        
	// Creates a bean that contains text that should be printed on the 
	// new page, setAfterSubmit = true means that the page will display
	// a message stating the insert was performed
        ApplicationBean applicationBean = new ApplicationBean();
        applicationBean.setAfterSubmit(true);
        return new ModelAndView("application", "command", applicationBean);
    }
     
    // This is called when /application is the input

    /**
     * When application.html is called, this shows the application page. 
     * @return* @return a ModelAndView of the application.jsp
     */
    @RequestMapping("/application")
    public ModelAndView showAppliers() {
	
	// This is used for all access to Expertise class in the database
	ApplicationExpertiseDataSourceManager appExpertiseDSM = new ApplicationExpertiseDataSourceManager();
	
	// Creates a new applicant and sets all of it's values
	ApplicationBean appBean = new ApplicationBean();
	appBean.setExpertiseList(appExpertiseDSM.getAllExpertises());
	
	// Just returns application jsp
        return new ModelAndView("application", "command", appBean);
    }
}