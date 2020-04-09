package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeManagerImplTest {


    private EmployeeManager manager;
    private EmployeeDaoImpl dao;
    private Employee obj;
    private EmployeeDTO dto;
    private int id = 34;
    private String firstName = "John";
    private String lastName = "bonham";
    private String phoneNumber = "038488474747";
    private String login = "logine";
    private String password="secret123";
    private List<Role> roles = new ArrayList<>();
    private String firstNameDto = "John";
    private String lastNameDto = "bonham";
    private String phoneNumberDto = "038488474747";
    private String loginDto = "logino";
    private List<String> roleDto= new ArrayList<>();

    @BeforeEach
    void init() {
        dao = mock(EmployeeDaoImpl.class);
        manager = EmployeeManagerImpl.builder().dao(dao).build();
        roles.add(Role.MANAGER);
        roleDto.add("manager");
        obj = Employee.builder().id(id).firstName(firstName).lastName(lastName).phoneNumber(phoneNumber).login(login).password(password).roles(roles).build();
        dto = EmployeeDTO.builder().id(id).firstName(firstNameDto).lastName(lastNameDto).phoneNumber(phoneNumberDto).login(loginDto).roles(roleDto).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    void entityToDto() {
        dto = (EmployeeDTO) manager.entityToDto(obj);
        String dtoRole = dto.getRoles().get(0);
        String objRole = obj.getRoles().get(0).toString();
        assertAll(
                () -> assertEquals(obj.getFirstName().toUpperCase(), dto.getFirstName()),
                () -> assertEquals(obj.getLastName().toUpperCase(), dto.getLastName()),
                () -> assertEquals(obj.getPhoneNumber().toUpperCase(), dto.getPhoneNumber()),
                () -> assertEquals(obj.getRoles().get(0).toString(), dto.getRoles().get(0))
        );
    }

    @Test
    void dtoToEntity() {
        obj = (Employee) manager.dtoToEntity(dto);
        System.out.println(obj.getRoles().get(0));
        System.out.println(dto.getRoles().get(0));
       /* assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getRoles().get(0), obj.getRoles().get(0).toString())
        );*/
    }




  /*  @Test
    @DisplayName("should not return exception if valid roles")
    void checkRoleValuesValid() {
        String[] rolesArray = {"MANAGER", "Mecanic"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertDoesNotThrow(
                ()-> manager.checkRoleValuesValid(rolesToCheck));

    }

    @Test
    @DisplayName("should return exception if invalid roles")
    void checkRoleValuesValid1() {
        String[] rolesArray = {"MANAGER", "Pharmacist"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertThrows(Exception.class,
                ()-> manager.checkRoleValuesValid(rolesToCheck));

    }*/




   /* @Test
    @DisplayName("should throw an exception is roles empty")
    void getByRoles() throws Exception {
        List<RoleListDTO> roleList = new ArrayList<>();
        RoleListDTO roleListDTO = new RoleListDTO();
        roleListDTO.setRole("role");
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(new EmployeeDTO());
        when(dao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> manager.getByRoles(roleList));
    }

    @Test
    @DisplayName("should return the list if role valid")
    void getByRoles2() throws Exception {
        String login = "rogoma";
        List<RoleListDTO> roleList = new ArrayList<>();
        RoleListDTO roleListDTO = new RoleListDTO();
        roleListDTO.setRole("mecanic");
        roleList.add(roleListDTO);
        List<Employee> list = new ArrayList<>();
        Employee employee = new Employee();
        employee.setLogin(login);
        list.add(employee);
        when(dao.getByRole(anyList())).thenReturn(list);
        assertEquals(login, manager.getByRoles(roleList).get(0).getLogin());
    }

    @Test
    @DisplayName("should throw an exception is roles has no name")
    void getByRoles1() throws Exception {
        List<RoleListDTO> roleList = new ArrayList<>();
        roleList.add(new RoleListDTO());
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(new EmployeeDTO());
        when(dao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> manager.getByRoles(roleList));
    }

    @Test
    @DisplayName("should throw an exception is roles invalid")
    void getByRoles3() throws Exception {
        List<RoleListDTO> roleList = new ArrayList<>();
        RoleListDTO roleListDTO = new RoleListDTO();
        roleListDTO.setRole("mos");
        roleList.add(roleListDTO);
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(new EmployeeDTO());
        when(dao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> manager.getByRoles(roleList));
    }

    @Test
    void getByLogin() {
        String login = "Bob56";
        Employee employee = new Employee();
        employee.setLogin(login);
        when(dao.getByLogin(anyString())).thenReturn(employee);
        assertEquals(login, manager.getEmployeeByLogin(login).getLogin());
    }*/
}
