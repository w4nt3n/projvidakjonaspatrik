/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Vidak
 */
@Controller
@SessionAttributes
public class IndexController{
    
    //@RequestMapping("/index")
    public ModelAndView showAppliers() {
	// SQL init code

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
	
	String sql = "CREATE TABLE pet (name VARCHAR(20));";
	Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                        conn.close();
                } catch (SQLException e) {}
            }
        }
        
        return new ModelAndView("application");
    }
}
