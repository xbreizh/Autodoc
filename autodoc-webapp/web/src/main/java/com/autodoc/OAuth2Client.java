/*
package com.autodoc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;


public class OAuth2Client {
    private static final String CRM_OAUTH2_URI = "http://localhost:8080/crm-oauth2/api/customers";
    private OAuth2RestTemplate oauth2RestTemplate = null;

    public static void main(String[] args) {
        OAuth2Client oauth2Client = new OAuth2Client();
        oauth2Client.runOAuth2Client();
    }

    public void runOAuth2Client() {
        //-- get the OAuth2 Rest template first
        oauth2RestTemplate = restTemplate();

        getCars();
//		getCustomer(3L);
//		createCustomer();
//		updateCustomer();
//		deleteCustomer(9L);
//		getCustomerEntity(8L);

    }

    */
/**
     *
     * get all Customers
     *
     *//*

    private void getCars() {
        Car[] cars = oauth2RestTemplate.getForObject(CRM_OAUTH2_URI, Car[].class);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    */
/**
     *
     * OAuth2 Rest template
     * @return
     *//*

    private OAuth2RestTemplate restTemplate() {
        System.out.println("getting OAuth2RestTemplate ...");

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setGrantType("password");
        resourceDetails.setAccessTokenUri("http://localhost:8088/autodoc/cars");

        //-- set the clients info
        resourceDetails.setClientId("crmClient1");
        resourceDetails.setClientSecret("crmSuperSecret");

        // set scopes
        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");
        scopes.add("trust");
        resourceDetails.setScope(scopes);

        //-- set Resource Owner info
        resourceDetails.setUsername("LMOLO");
        resourceDetails.setPassword("password");

        return new OAuth2RestTemplate(resourceDetails);
    }

}
*/
