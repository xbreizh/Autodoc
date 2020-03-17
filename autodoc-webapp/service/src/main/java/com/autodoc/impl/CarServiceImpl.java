package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import javax.inject.Named;

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

    /*@Override
    public int update(String token, Object object) {
        LOGGER.info("updating service");
     //   CarDTO dto = getDtoObject((CarDTO) object);
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<CarDTO> requestUpdate = new HttpEntity<>((CarDTO)object, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }*/

   /* @Override
    public String create(String token, Object object) {
      //  CarDTO dto = getDtoObject((CarDTO) object);
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        //HttpEntity<EmployeeDTO> requestInsert = new HttpEntity<>(dto, headers);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(getDtoObject(object), headers), String.class).getBody();
        } catch (HttpClientErrorException error) {
            String errorDetails = error.getResponseBodyAsString();
            LOGGER.info(errorDetails);
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return errorDetails;
        }*/
       /* LOGGER.info("class: " + getClassName());
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<CarDTO> requestInsert = new HttpEntity<>(dto, headers);
        try {
            String response = restTemplate.exchange(url, HttpMethod.POST, requestInsert, Integer.class).getBody().toString();
            return response;
        } catch (RuntimeException error) {
            LOGGER.info("er: " + error.getLocalizedMessage());

            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
            }
            return error.getLocalizedMessage().substring(0, 3);
        }*/
/*
    }
*/

/*
    private CarDTO getDtoObject(Object object) {
        return (CarDTO) object;
    }
*/


}
