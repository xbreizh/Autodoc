package com.autodoc.business.impl.authentication;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;

@Named
public class JwtConnect implements UserDetailsService {
    private static final Logger LOGGER = Logger.getLogger(JwtConnect.class);
    private EmployeeManager employeeManager;

    public JwtConnect(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        //User authentication management
        Employee employee = employeeManager.getEmployeeByLogin(login);
        LOGGER.debug("");
        if (employee != null) {
            LOGGER.debug("found: " + login);
            // create token mgt entity and token generation config
            if (employee.getLogin().equals(login)) {
                LOGGER.debug("new password: " + new BCryptPasswordEncoder().encode("abc123"));

                // return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                //User user = new User(login, "$2a$10$uY/HyJBjWPp9DXAyuEGUJu2wGzldUhkTu7CUPuaZeCjoo3Ig3CWn2",
                User user = new User(login, employee.getPassword(),
                        new ArrayList<>());
                LOGGER.debug("user: " + user);
                try {
                    updatingEmployeeConnectionDetails(employee);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + login);
    }

    private void updatingEmployeeConnectionDetails(Employee employee) throws Exception {
        EmployeeDTO dto = employeeManager.getEmployeeDtoByLogin(employee.getLogin());
        dto.setLastConnection(new Date());
        LOGGER.info("adding last connection");
        employeeManager.update(dto);
    }

    public void checkToken(String token) {
        //User authentication management
        Employee employee = employeeManager.getByToken(token);
        LOGGER.debug("employee: " + employee);
        if (employee != null && tokenIsExpired(token)) {
            throw new UsernameNotFoundException("Token expired");
        }
        throw new UsernameNotFoundException("Invalid token: " + token);
    }

    private boolean tokenIsExpired(String token) {
        return false;
    }

}
