/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import ActiveRecord.ApplicationListBean;
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

/**
 *
 * @author Vidak
 */

@Controller
@SessionAttributes
public class ApplicationListController {
 
    @RequestMapping("/applicationList")
    public ModelAndView showAppliers() {
        
        ApplicationListBean applicationBean = new ApplicationListBean();
        Person person1 = new Person("Vidak", "Mijailovic", 21, "email@mail.com", "+46704470905");
        Person person2 = new Person("Vidak", "Mijailovic", 21, "email@mail.com", "+46704470905");
        Person person3 = new Person("Vidak", "Mijailovic", 21, "email@mail.com", "+46704470905");
        
        applicationBean.addPerson(person1);
        applicationBean.addPerson(person2);
        applicationBean.addPerson(person3);
        
        ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
        
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");
        applicationBean.setAllApplications(personDAO.getAllPersons());
        
        return new ModelAndView("applicationList", "message", applicationBean);
    }
}
