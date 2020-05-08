package com.autodoc.controllers.impl.authentication;

import com.autodoc.business.impl.authentication.JwtConnect;
import com.autodoc.business.impl.authentication.JwtRequest;
import com.autodoc.business.impl.authentication.JwtResponse;
import com.autodoc.business.impl.authentication.JwtTokenUtil;
import com.autodoc.controllers.helper.GsonConverter;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class JwtAuthenticationController {

    private static Logger LOGGER = Logger.getLogger(JwtAuthenticationController.class);
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private JwtTokenUtil jwtTokenUtil;
    @Inject
    private JwtConnect jwtConnect;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        LOGGER.debug("create autjhentication token");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtConnect
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LOGGER.debug("token returned: " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        LOGGER.debug("username: " + username);
        LOGGER.debug("pwd: " + password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private void checkToken(String token) throws Exception {
        LOGGER.debug("token: " + token);
        String token2 = new GsonConverter().convertObjectIntoGsonObject(token);
        LOGGER.debug("token2: " + token2);
        try {
            jwtConnect.checkToken(token2);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}