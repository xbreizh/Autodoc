package com.autodoc.business.impl.authentication;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Named;
import java.util.ArrayList;

@Named
public class JwtConnect implements UserDetailsService {
    private Logger logger = Logger.getLogger(JwtConnect.class);
    private EmployeeManager employeeManager;

    public JwtConnect(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        //User authentication management
        Employee employee = employeeManager.getEmployeeByLogin(login);
        if (employee != null) {
            System.out.println("found: "+login);
            // TODO
            // create token mgt entity and token generation config
            if (employee.getLogin().equals(login)) {

                // return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                //User user = new User(login, "$2a$10$uY/HyJBjWPp9DXAyuEGUJu2wGzldUhkTu7CUPuaZeCjoo3Ig3CWn2",
                User user = new User(login, employee.getPassword(),
                        new ArrayList<>());
                logger.debug("user: " + user);
                System.out.println("user: " + user);
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + login);
    }


}
