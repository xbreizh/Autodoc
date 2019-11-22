package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.models.Car;
import com.autodoc.model.dtos.CarDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import javax.inject.Named;

@Named
public class CarServiceImpl extends GlobalServiceImpl<Car> implements CarService {
    private static Logger LOGGER = Logger.getLogger(CarServiceImpl.class);

    Class getObjectClass() {
        return Car.class;
    }

    @Override
    public CarDTO getByRegistration(String token, String registration) {
        LOGGER.info("trying to get car by registration");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("registration: " + registration);
            String url = BASE_URL + getClassName() + "/registration?registration=" + registration;
            LOGGER.info("url: " + url);
            ResponseEntity<CarDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, CarDTO.class);
            LOGGER.info("object found: " + response.getBody());
            //checkObjectFields()
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


}
