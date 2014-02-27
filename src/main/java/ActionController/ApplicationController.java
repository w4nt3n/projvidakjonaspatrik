/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

/**
 *
 * @author Jonas
 */
 
import ActiveRecord.ApplicationBean;
import ActiveRecord.Person;
import ActiveRecord.PersonDAO;
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
                            @RequestParam("birthdayDaySelect") String birthdayDaySelect) {
        
        // Creates a bean for MySQL PersonDAO object.
	// Spring-Module.xml contains references to Spring-Datasource.xml
	// and Spring-Person.xml, these contain beans for Person and database login
        ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
	// Fetches the personDAO bean
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");
        
	// Creates a new person and sets all of it's values
        Person person = new Person();
        person.setName(application.getFirstname());
        person.setSurame(application.getSurname());
        String sqlDateFormat = birthdayYearSelect + "-" + birthdayMonthSelect + "-" + birthdayDaySelect;
        person.setBirthDate(sqlDateFormat);
        person.setEmail(application.getEmail());
        person.setTelephone(application.getTelephone());
        
	// Adds the new person to the database
        personDAO.insert(person);
        
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
	// Just returns application jsp
        return new ModelAndView("application", "command", new ApplicationBean());
    }
}