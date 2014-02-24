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
    public String addApplier(@ModelAttribute("application")
                            ApplicationBean application, BindingResult result) {
         
        System.out.println("First Name:" + application.getFirstname() +
                    "Last Name:" + application.getLastname());
         
        return "redirect:application.htm";
    }
     
    @RequestMapping("/application")
    public ModelAndView showAppliers() {
         
        return new ModelAndView("application", "command", new ApplicationBean());
    }
}