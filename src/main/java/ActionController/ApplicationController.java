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
import ActiveRecord.ApplicantAvailability;
import ActiveRecord.ApplicantExperience;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
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
     * @param inputAvailability
     * @param request
     * @param response
     * @return a ModelAndView of the application.jsp
     * @throws javax.servlet.ServletException
     * @throws java.lang.Exception
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/addApplier", method = RequestMethod.POST)
    public ModelAndView addApplier(@ModelAttribute("application") ApplicationBean application, BindingResult result, 
                            @RequestParam("dropdownYear") String dropdownYear,
                            @RequestParam("dropdownMonth") String dropdownMonth,
                            @RequestParam("dropdownDay") String dropdownDay,
			    @RequestParam("inputExperience") String inputExperience,
                            @RequestParam("inputAvailability") String inputAvailability,
			    HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, Exception{
        
        // Validate input server side
	if (!application.Validate() &&
                dropdownYear.length() > 0 &&
                dropdownMonth.length() > 0 &&
                dropdownDay.length() > 0) {
            application.hasError(true, "Invalid Inputs");
            return new ModelAndView("application", "command", application);
        }
	
	ArrayList<ApplicantExperience> appExpList = new ArrayList<>();
	ArrayList<ApplicantAvailability> appAvDateList = new ArrayList<>();
	
	// This is used for all access to Expertise class in the database
	ApplicationExpertiseDataSourceManager appExpertiseDSM = new ApplicationExpertiseDataSourceManager();
	
	//------------------------------------------------------------
	//----------------------- Parses all of the expertises -------
	
	String experience[] = inputExperience.split(",");
	ApplicantExperience appExp;
        for(int i = 0; i < experience.length; i+=2) {
	    appExp = new ApplicantExperience();
	    try{
		appExp.setExpertise(appExpertiseDSM.getIdWithExpertise(experience[i]));
	    }catch(Exception e){
		application.hasError(true, "Could not acces the database");
		return new ModelAndView("application", "command", application);
	    }
            appExp.setYears(Integer.parseInt(experience[i+1]));

            appExpList.add(appExp);
        }
	
	String availability[] = inputAvailability.split(",");
	ApplicantAvailability appAv;
        for(int i = 0; i < availability.length; i+=2) {
	    appAv = new ApplicantAvailability();
            appAv.setFrom(availability[i]);
            appAv.setTo(availability[i+1]);

            appAvDateList.add(appAv);
        }
	
	
//	response.setContentType("text/html;charset=UTF-8");
//	try (PrintWriter out = response.getWriter()) {
//
//	    out.println("<!DOCTYPE html>");
//	    out.println("<html>");
//	    out.println("<head>");
//	    out.println("<title>Servlet NewServlet</title>");	    
//	    out.println("</head>");
//	    out.println("<body>");
//	    for(int i = 0; i < appAvDateList.size(); i++) {
//		out.println("\"" + appAvDateList.get(i).getApplicantID() + "\"<br />");
//		out.println("\"" + appAvDateList.get(i).getFrom() + "\"<br />");
//		out.println("\"" + appAvDateList.get(i).getTo() + "\"<br />");
//	    }
//	    out.println("</body>");
//	    out.println("</html>");
//
//	}
	
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
	try{
	    appDSM.insert(applicant);
	} catch(Exception e){
	    application.hasError(true, "Could not add application to server");
            return new ModelAndView("application", "command", application);
	}
	
	//------------------------------------------------------------
	//----------------------- Writes to the availability ---------
	// This is used for all access to Applicant class in the database
	ApplicationAvailabilityDataSourceManager appAvDSM = new ApplicationAvailabilityDataSourceManager();
	
	ArrayList<Applicant> appThatMatch = null;
	// Get the ID of the newly added appicant
	try{
	    appThatMatch = appDSM.getApplicantIDWhere("name='" + applicant.getFirstname() + "' AND"
		    + " surname='" + applicant.getLastname() + "' AND"
		    + " dateOfBirth='" + applicant.getDateOfBirth() + "' AND"
		    + " email='" + applicant.getEmail() + "' AND"
		    + " telephone='" + applicant.getPhone() + "'");
	} catch(Exception e){
	    application.hasError(true, "Could not get application ID");
            return new ModelAndView("application", "command", application);
	}
	
	if(appThatMatch.size() == 1){
	    for(int i = 0; i < appAvDateList.size(); i++){
		appAvDateList.get(i).setApplicantID(appThatMatch.get(0).getId());
		appAvDSM.insert(appAvDateList.get(i));
	    }
	}
	else{
	    application.hasError(true, "This user already exists in the database");
            return new ModelAndView("application", "command", application);
	}
	//------------------------------------------------------------
	//----------------------- Writes to the experience ------------
	
	
	if(appExpList.size() >= 1){
	    
	    // This is used for all access to Experience class in the database
	    ApplicationExperienceDataSourceManager appExperienceDSM = new ApplicationExperienceDataSourceManager();
	    
	    // Creates a new applicant and sets all of it's values
	    ApplicantExperience applicantExperience = null;
	    // Get the ID of the newly added appicant
	    
	    
	    for(int i = 0; i < appExpList.size(); i++){
		// Creates a new applicant and sets all of it's values
		applicantExperience = new ApplicantExperience();
		
		// Will be true, error above else
		if(appThatMatch.size() == 1){
		    // There should only be one that matches, lets make an error message if there are more than one later
		    applicantExperience.setApplicantID(appThatMatch.get(0).getId());
		    applicantExperience.setExpertise(appExpList.get(i).getExpertise());
		    applicantExperience.setYears(appExpList.get(i).getYears());

		    // Adds the new applicant to the database
		    try{
			appExperienceDSM.insert(applicantExperience);
		    } catch(Exception e){
			application.hasError(true, "Could not add the applicant experiences to database");
			return new ModelAndView("application", "command", application);
		    }
		}
	    }
	}
	//------------------------------------------------------------
        
	// Creates a bean that contains text that should be printed on the 
	// new page, setAfterSubmit = true means that the page will display
	// a message stating the insert was performed
        application.setAfterSubmit(true);
        return new ModelAndView("application", "command", application);
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
	ApplicationBean application = new ApplicationBean();
	
	try{
	    application.setExpertiseList(appExpertiseDSM.getAllExpertises());
	} catch(Exception e){
	    application.hasError(true, "Could not connect to the database");
	}
	// Just returns application jsp
        return new ModelAndView("application", "command", application);
    }
}