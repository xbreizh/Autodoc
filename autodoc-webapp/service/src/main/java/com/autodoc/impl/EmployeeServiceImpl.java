package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<EmployeeDTO> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return EmployeeDTO.class;
    }
    Class getListClass() {
        return EmployeeDTO[].class;
    }


   /* @Override
    public int update(String token, Object object) {
       // Object dto = getDto(object);
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        //HttpEntity<EmployeeDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>((EmployeeDTO)object, headers), Void.class).getStatusCodeValue();

    }*/





/*
    private EmployeeDTO getDto(Object object) {
        return getDto((EmployeeDTO) object);
    }
*/

   /* @Override
    public String create(String token, Object object) {
       // EmployeeDTO dto = (EmployeeDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>((EmployeeDTO)object, headers), String.class).getBody();
        } catch (HttpClientErrorException error) {
            String errorDetails = error.getResponseBodyAsString();
            LOGGER.info(errorDetails);
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return errorDetails;
        }
    }
*/

}

