package com.autodoc.impl;

import com.autodoc.contract.PieceTypeService;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class PieceTypeServiceImpl extends GlobalServiceImpl<PieceTypeDTO> implements PieceTypeService {
    private static Logger LOGGER = Logger.getLogger(PieceTypeServiceImpl.class);

    Class getObjectClass() {
        return PieceTypeDTO.class;
    }
    Class getListClass() {
        return PieceTypeDTO[].class;
    }


    public String getClassName() {
        return "pieceTypes";
    }


/*    @Override
    public int update(String token, Object object) {
        PieceTypeDTO dto = (PieceTypeDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<PieceTypeDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }*/


 /*   @Override
    public String create(String token, Object object) {
        PieceTypeDTO dto = (PieceTypeDTO) object;
        LOGGER.info("class: " + getClassName());
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<PieceTypeDTO> requestInsert = new HttpEntity<>(dto, headers);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Void.class).getBody().toString();
        } catch (RuntimeException error) {
            LOGGER.info("er: " + error.getLocalizedMessage());
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
            }
            return error.getLocalizedMessage().substring(0, 3);
        }
    }*/


}

