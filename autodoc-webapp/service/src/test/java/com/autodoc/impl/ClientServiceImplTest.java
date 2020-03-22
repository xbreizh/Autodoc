package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImplTest.class);
    private ClientService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU4NDU0Mjc2MywiaWF0IjoxNTg0NTI0NzYzfQ.HvyVU1IoPObGSLQuUVHd2jUVS53PuV1tsmc6JVycey3EsZlVNgnSXIO6PUnByceAUwiamTEmbKJUzsk8cRZzEg";
    private ClientDTO dto;

   /* @BeforeEach
    String getToken(){
        String login = "lmolo";
        String password = "password";
        String role = "user";
        Authentication authToken = new UsernamePasswordAuthenticationToken(login, password, Arrays.asList(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        String newToken = new ConnectManagerImpl().authenticate(authToken).getDetails().toString();
        token = newToken.replace("{\"token\":\"", "");
        token = newToken.replace("\"}", "");
        return token;
    }*/

    @BeforeEach
    void init() {
        service = new ClientServiceImpl();
        dto = new ClientDTO();
        dto.setLastName("john");
        dto.setFirstName("ssss");
        dto.setPhoneNumber("32323232323");
    }


    @Test
    void getObjectClass() {
    }


    @Test
    @DisplayName("should return client id when insertion ok")
    void create() {
        //System.out.println("feedback: " + service.create(token, dto));
        assertEquals(201, service.create(token, dto));


    }


}