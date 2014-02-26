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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@SessionAttributes
public class ApplicationController {
 
    @RequestMapping(value = "/addApplier", method = RequestMethod.POST)
    public ModelAndView addApplier(@ModelAttribute("application")
                            ApplicationBean application, BindingResult result) {
        
        // adds the application to the database
        ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
        
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");
        Person person = new Person(application.getFirstname(), application.getSurname(), application.getEmail(), application.getTelephone());
        personDAO.insert(person);
        
        ApplicationBean applicationBean = new ApplicationBean();
        applicationBean.setAfterSubmit(true);
        return new ModelAndView("application", "command", applicationBean);
    }
     
    @RequestMapping("/application")
    public ModelAndView showAppliers() {
        return new ModelAndView("application", "command", new ApplicationBean());
    }
}