package com.autodoc.business.impl;

import com.autodoc.model.Employee;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

class EmployeeManagerImplTest {
    private static final String baseUrl = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3Mzk0NTA2OSwiaWF0IjoxNTczOTI3MDY5fQ.PaJrJ9e3A66aPw4yaAQbjbz5WrFcmdtfEh-w2NU4f5fyR0l_CZyT3md_2Xxc6gv-NB2sjH8r_yMJOpzk2XVS7g";
    String url = "http://localhost:8087/autodoc/employees";
    /* private HttpHeaders createHttpHeaders()
     {
         //String notEncoded = user + ":" + password;
         //String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.add("Bearer", token);
         return headers;
     }
 */





    @Test
    @DisplayName("should return a valid token")
    public void getEmployeeByLogin()
            throws IOException {

        //JSONObject jsonObject;
        //RestTemplate restTemplate = new RestTemplate();

        // restTemplate = new RestTemplate();
        //HttpHeaders headers = new HttpHeaders();

        //headers.setBearerAuth(token);
        //jsonObject = new JSONObject();
        //jsonObject.put("name", "LMOLO");
       // String url = "http://localhost:8087/autodoc/employees";
        String login = "LMOLO";
        // HttpEntity<String> request =                new HttpEntity<String>(jsonObject.toString(), headers);

        /* String employee =*/
        //String theUrl = "http://blah.blah.com:8080/rest/api/blah";
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        Gson gson = new Gson();
        //Create a new HttpEntity
       // final HttpEntity<String> entity = new HttpEntity<String>(headers);

        //  ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate();
       // HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<Object>("parameters", headers);
        ResponseEntity<Employee> res = restTemplate.exchange(url+"/name?name="+login, HttpMethod.GET, entity, Employee.class);
        System.out.println("date: "+res.getBody().getStartDate());
        System.out.println("firstname: "+res.getBody().getFirstName());
        /*String jsonObj = gson.toJson(res.getBody());
        System.out.println(jsonObj);


        Gson gson2 = new Gson();
        Employee employee = gson2.fromJson("{"+jsonObj+"}", Employee.class);*/
        System.out.println("employee: "+res.getBody());
        Employee employee = res.getBody();
        System.out.println("employee: "+employee);
        //Object policies=  jsonObj.get("policies");
        //System.out.println(response.getBody());

        //HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        //String result = restTemplate.getForObject(url,  String.class);
        //restTemplate.getForObject(url,  Employee.class);
        /*JsonNode root = objectMapper.readTree(token);
        System.out.println("tokent: "+employee);
        assertNotNull(token);
        assertNotNull(root);*/
        //System.out.println("object returned: "+employee);
        //assertNotNull(root.path("name").asText());
    }
}