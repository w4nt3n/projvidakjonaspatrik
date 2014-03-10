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
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
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
     * @param dropdownYear
     * @param dropdownMonth
     * @param dropdownDay
     * @param inputExperience
     * @return a ModelAndView of the application.jsp
     */
    @RequestMapping(value = "/addApplier", method = RequestMethod.POST)
    public ModelAndView addApplier(@ModelAttribute("application")
                            ApplicationBean application, BindingResult result, 
                            @RequestParam("dropdownYear") String dropdownYear,
                            @RequestParam("dropdownMonth") String dropdownMonth,
                            @RequestParam("dropdownDay") String dropdownDay,
			    @RequestParam("inputExperience") String inputExperience){
        
	ArrayList<ApplicantExperience> appExpList = new ArrayList<>();
	
	// This is used for all access to Expertise class in the database
	ApplicationExpertiseDataSourceManager appExpertiseDSM = new ApplicationExpertiseDataSourceManager();
	
	//------------------------------------------------------------
	//----------------------- Parses all of the expertises -------
	
	/*String stringBuffer = "";
	int mode = 1;
	ApplicantExperience appExp = new ApplicantExperience();
	for (int i = 0; i < inputExperience.length(); i++){	// Goes through all of the characters
	    if (inputExperience.charAt(i) == ','){	// If the next character is a tab
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
		stringBuffer = stringBuffer + inputExperience.charAt(i);
	}*/
        
        String experience[] = inputExperience.split(",");
        ApplicantExperience appExp;
        for(int i = 0; i+2 <= experience.length; i+=2) {
            appExp = new ApplicantExperience();
            appExp.setExpertise(appExpertiseDSM.getIdWithExpertise(experience[i]));
            appExp.setYears(Integer.parseInt(experience[i+1]));

            appExpList.add(appExp);
        }
	
	//------------------------------------------------------------
	//----------------------- Writes to the applicant ------------
	
	// This is used for all access to Applicant class in the database
	ApplicationDataSourceManager appDSM = new ApplicationDataSourceManager();
	
	// Creates a new applicant and sets all of it's values
        Applicant applicant = new Applicant();
        applicant.setFirstname(application.getFirstname());
        applicant.setLastname(application.getLastname());
        String sqlDateFormat = dropdownYear + "-" + dropdownMonth + "-" + dropdownDay;
        applicant.setDateOfBirth(sqlDateFormat);
        applicant.setEmail(application.getEmail());
        applicant.setPhone(application.getPhone());
        
	// Adds the new applicant to the database
        appDSM.insert(applicant);
	
	//------------------------------------------------------------
	//----------------------- Writes to the experience ------------
	
	if(!appExpList.isEmpty()){
	    
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
		appThatMatch = appDSM.getApplicantIDWhere("name='" + applicant.getFirstname() + "' AND"
			+ " surname='" + applicant.getLastname() + "' AND"
			+ " dateOfBirth='" + applicant.getDateOfBirth() + "' AND"
			+ " email='" + applicant.getEmail() + "' AND"
			+ " telephone='" + applicant.getPhone() + "'");
		
		if(appThatMatch.size() == 1){
		    // There should only be one that matches, lets make an error message if there are more than one later
		    applicantExperience.setApplicantID(appThatMatch.get(0).getId());
		    applicantExperience.setExpertise(appExpList.get(i).getExpertise());
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
