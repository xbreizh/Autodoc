package com.autodoc.business.contract;


import com.autodoc.model.Car;
import com.autodoc.model.Employee;

import java.util.List;

public interface CarManager {


    Car getByRegistration(String token, String registration);

    Car getById(String token, int id);

    //List<Employee> getEmployeeList(String token);

   /* boolean resetPassword(String login, String password, String token) throws BusinessExceptionConnect;

    boolean sendResetPasswordLink(String login, String email) throws BusinessExceptionConnect;

    boolean switchReminder(String token, String login, boolean reminder) throws BusinessExceptionMember;*/


}
