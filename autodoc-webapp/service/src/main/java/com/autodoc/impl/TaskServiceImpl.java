package com.autodoc.impl;

import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class TaskServiceImpl extends GlobalServiceImpl<TaskDTO> implements TaskService {
    private static Logger LOGGER = Logger.getLogger(TaskServiceImpl.class);

    Class getObjectClass() {
        return TaskDTO.class;
    }

    Class getListClass() {
        return TaskDTO[].class;
    }

    public String getClassName() {
        return "tasks";
    }


  /*  @Override
    public int updateTemplate(String token, Object object) {
        TaskDTO dto = (TaskDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName() + "/templates";
        LOGGER.info("obj: " + dto);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<TaskDTO> requestUpdate = new HttpEntity<>(dto, headers);
        int codeValue = restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();
        LOGGER.info("codeValue: " + codeValue);
        return codeValue;

    }
*/


}

