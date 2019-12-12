package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.inject.Named;
import java.util.Collections;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<EmployeeDTO> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return EmployeeDTO.class;
    }


    @Override
    public void update(String token, Object object) {
        EmployeeDTO dto = (EmployeeDTO) object;
        // setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);

        //ResponseEntity<EmployeeDTO> response = restTemplate.put(url,  request,  dto);
        /*Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");*/

        //  EmployeeDTO updatedEmployee = new EmployeeDTO(2, "New Name", "Gilly", "test@email.com");

        //   RestTemplate restTemplate = new RestTemplate();
        //  restTemplate.put ( url, dto);
       /* HttpEntity<EmployeeDTO> requestEntity = new HttpEntity<EmployeeDTO>(dto);
        ResponseEntity<EmployeeDTO[]> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, EmployeeDTO[].class);*/
       /* Foo updatedInstance = new Foo("newName");
        updatedInstance.setId(createResponse.getBody().getId());*/
       /* String resourceUrl =
                fooResourceUrl + '/' + createResponse.getBody().getId();*/
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<EmployeeDTO> requestUpdate = new HttpEntity<>(dto, headers);
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);

    }
       /* if (response.getStatusCodeValue() == 404) System.out.println("not found");


        if (response.getStatusCodeValue() == 200) System.out.println("updated");*/






 /*   @Override
    public CarDTO getByRegistration(String token, String registration) {
        LOGGER.info("trying to get car by registration");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("registration: " + registration);
            String url = BASE_URL + getClassName() + "/registration?registration=" + registration;
            LOGGER.info("url: " + url);
            //System.out.println("retour: "+restTemplate.exchange(url, HttpMethod.GET, request, CarDTO.class));
            ResponseEntity<CarDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, CarDTO.class);
            LOGGER.info("object found: " + response);
            if (response.getStatusCodeValue() == 404) return null;
            //checkObjectFields()
            return response.getBody();
        } catch (Exception e) {
            System.out.println("before");
            if (e.getMessage().equals("404 null")) return null;
            System.out.println("after");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }*/


}

