/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

/**
 *
 * @author Vidak
 */
import ActiveRecord.MySQLTestBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ActiveRecord.Person;
import ActiveRecord.PersonDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
@Controller
@SessionAttributes
public class MySQLTestController {
    
    @RequestMapping("/mysqltest")
    public ModelAndView showAppliers() {
        
        ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
        
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");
        Person person = new Person("vidak", "mijailovic", 21, "mina@mail.com", "0707777777");
        personDAO.insert(person);
 
        Person customer1 = personDAO.findByPersonId(1);
        
        MySQLTestBean myBean = new MySQLTestBean();
        myBean.setMessage(customer1.getName());
        
        return new ModelAndView("mysqltest", "message", myBean);
    }
}