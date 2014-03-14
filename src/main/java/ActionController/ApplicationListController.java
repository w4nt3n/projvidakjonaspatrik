/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicationListBean;
import ActiveRecord.ApplicantDAO;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
public class ApplicationListController {
    
    /**
     * This method is called when the user loads /applicationList.html. This method 
     * simply lists all of the users in a neat table.
     * <p>
     * This method fetches all of the applicants and stores them in an ArrayList of applicants.
     * This array list exists in the applicationListBean and is printed out by the applicationList.jps
     * @return applicationList ModelAndView with applicationListBean
     */
    @RequestMapping("/applicationList")
    public ModelAndView showAppliers() {
        
	// Creates a bean for the applicationList.jsp
        ApplicationListBean applicationListBean = new ApplicationListBean();
        
	// This is used for all access to Applicant class in the database
	ApplicationDataSourceManager appDSM = new ApplicationDataSourceManager();
	
	// Fetches all of the applicants. getALlApplicants() returns ArrayList<Applicant>
	// this list is sent to applicationListBean
	try{
	    applicationListBean.setAllApplications(appDSM.getAllApplicants());
	}catch(Exception e){
	    applicationListBean.hasError(true);
	}
	
	// Sends the bean and reurns the page
        return new ModelAndView("applicationList", "message", applicationListBean);
    }
}
