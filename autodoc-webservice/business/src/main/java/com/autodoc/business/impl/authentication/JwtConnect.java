package com.autodoc.business.impl.authentication;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //User authentication management


        // TODO
        // create token mgt entity and token generation config
        if ("javainuse".equals(username)) {

            // return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
            User user = new User("javainuse", "$2a$10$uY/HyJBjWPp9DXAyuEGUJu2wGzldUhkTu7CUPuaZeCjoo3Ig3CWn2",
                    new ArrayList<>());
            logger.debug("user: " + user);
            System.out.println("user: " + user);
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


}
