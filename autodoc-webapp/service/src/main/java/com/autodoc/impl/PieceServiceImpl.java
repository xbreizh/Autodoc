package com.autodoc.impl;

import com.autodoc.contract.PieceService;
import com.autodoc.model.dtos.pieces.PieceDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class PieceServiceImpl extends GlobalServiceImpl<PieceDTO> implements PieceService {
    private static Logger LOGGER = Logger.getLogger(PieceServiceImpl.class);

    Class getObjectClass() {
        return PieceDTO.class;
    }
    Class getListClass() {
        return PieceDTO[].class;
    }


    public String getClassName() {
        return "pieces";
    }


/*    @Override
    public int update(String token, Object object) {
        PieceDTO dto = (PieceDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<PieceDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }*/


  /*  @Override
    public String create(String token, Object object) {
        PieceDTO dto = (PieceDTO) object;
        LOGGER.info("class: " + getClassName());
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("piece: " + dto);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<PieceDTO> requestInsert = new HttpEntity<>(dto, headers);
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

