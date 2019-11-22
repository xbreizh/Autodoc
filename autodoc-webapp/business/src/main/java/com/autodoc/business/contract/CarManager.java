package com.autodoc.business.contract;


import com.autodoc.model.models.Car;

public interface CarManager extends GlobalManager{


   // Car getByRegistration(String token, String registration);

    Car getById(String token, int id);

    Car getByRegistration(String connectedToken, String registration);

    //List<Employee> getEmployeeList(String token);

   /* boolean resetPassword(String login, String password, String token) throws BusinessExceptionConnect;

    boolean sendResetPasswordLink(String login, String email) throws BusinessExceptionConnect;

    boolean switchReminder(String token, String login, boolean reminder) throws BusinessExceptionMember;*/


}
