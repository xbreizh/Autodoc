package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;

import javax.inject.Named;
import java.util.Collections;

@Named
public class CarServiceImpl extends GlobalServiceImpl<CarDTO> implements CarService {
    private static Logger LOGGER = Logger.getLogger(CarServiceImpl.class);

    Class getObjectClass() {
        return CarDTO.class;
    }
    Class getListClass() {
        return CarDTO[].class;
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
            //LOGGER.info("retour: "+restTemplate.exchange(url, HttpMethod.GET, request, CarDTO.class));
            ResponseEntity<CarDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, CarDTO.class);
            LOGGER.info("object found: " + response);
            if (response.getStatusCodeValue() == 404) return null;
            //checkObjectFields()
            return response.getBody();
        } catch (Exception e) {
            LOGGER.info("before");
            if (e.getMessage().equals("404 null")) return null;
            LOGGER.info("after");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public int update(String token, Object object) {
        LOGGER.info("updating service");
        CarDTO dto = (CarDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<CarDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }


}
