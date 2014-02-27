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
import java.util.ArrayList;
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
			    @RequestParam("workExpertiseInput") String workExpertiseInput) {
        
	ArrayList<ApplicantExperience> appExpList = new ArrayList<ApplicantExperience>();
	
	String stringBuffer = "";
	int mode = 1;
	ApplicantExperience appExp = new ApplicantExperience();
	for(int i = 0; i < workExpertiseInput.length(); i++){	// Goes through all of the characters
	    if(workExpertiseInput.charAt(i) == '\t'){	// If the next character is a tab
		if(stringBuffer.length() >= 1){		// and we currently have something in the buffer
		    if(mode == 1){			// and we are searching for an expertise
			appExp.setExpertiese(stringBuffer); // set the expertise in the current applicantExpertise
			stringBuffer = "";		    // clear the buffer
			mode = 2;			    // start search for years
		    }
		    else if(mode == 2){			    // if searching for years
			appExp.setYears(Integer.parseInt(stringBuffer));    // set the years in the current applicantExpertise
			stringBuffer = "";				    // clear the buffer
			mode = 1;					    // start search for expertise
			appExpList.add(appExp);				    // add expertise to expertise list
			appExp = new ApplicantExperience();		    // create a new expertise
		    }
		}
		else{
		    stringBuffer = stringBuffer + workExpertiseInput.charAt(i);
		}
	    }
	    else{
		stringBuffer = stringBuffer + workExpertiseInput.charAt(i);
	    }
	}
	
	
	
        // Creates a bean for MySQL ApplicantDAO object.
	// Spring-Module.xml contains references to Spring-Datasource.xml
	// and Spring-Applicant.xml, these contain beans for Applicant and database login
        ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
	// Fetches the applicantDAO bean
        ApplicantDAO applicantDAO = (ApplicantDAO) context.getBean("applicantDAO");
        
	// Creates a new applicant and sets all of it's values
        Applicant applicant = new Applicant();
        applicant.setName(application.getFirstname());
        applicant.setSurame(application.getSurname());
        String sqlDateFormat = birthdayYearSelect + "-" + birthdayMonthSelect + "-" + birthdayDaySelect;
        applicant.setDateOfBirth(sqlDateFormat);
        applicant.setEmail(application.getEmail());
        applicant.setTelephone(application.getTelephone());
        
	// Adds the new applicant to the database
        applicantDAO.insert(applicant);
        
	// Creates a bean that contains text that should be printed on the 
	// new page, setAfterSubmit = true means that the page will display
	// a message stating the insert was performed
        ApplicationBean applicationBean = new ApplicationBean();
        applicationBean.setAfterSubmit(true);
	if(appExpList.size() >= 1)
	    applicationBean.setFirstname(appExpList.get(0).geExpertiese());
        return new ModelAndView("application", "command", applicationBean);
    }
     
    // This is called when /application is the input

    /**
     * When application.html is called, this shows the application page. 
     * @return* @return a ModelAndView of the application.jsp
     */
        @RequestMapping("/application")
    public ModelAndView showAppliers() {
	// Just returns application jsp
        return new ModelAndView("application", "command", new ApplicationBean());
    }
}