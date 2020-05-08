package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
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
    private String password = "secret123";
    private List<Role> roles = new ArrayList<>();
    private String firstNameDto = "John";
    private String lastNameDto = "bonham";
    private String phoneNumberDto = "038488474747";
    private String loginDto = "logino";
    private List<String> roleDto = new ArrayList<>();

    @BeforeEach
    void init() {
        BasicConfigurator.configure();
        dao = mock(EmployeeDaoImpl.class);
        manager = EmployeeManagerImpl.builder().dao(dao).build();
        roles.add(Role.MANAGER);
        roleDto.add("manager");
        obj = Employee.builder().id(id).firstName(firstName).lastName(lastName).phoneNumber(phoneNumber).login(login).password(password).roles(roles).build();
        dto = EmployeeDTO.builder().id(id).firstName(firstNameDto).lastName(lastNameDto).phoneNumber(phoneNumberDto).login(loginDto).password(password).roles(roleDto).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should return className")
    void getEntityClass() {
        assertEquals(Employee.class, manager.getEntityClass());
    }

    @Test
    @DisplayName("should return dtoClassName")
    void getDtoClass() {
        assertEquals(EmployeeDTO.class, manager.getDtoClass());
    }

    @Test
    @DisplayName("should return true if employee exists")
    void exist() {
        when(dao.getByLogin(anyString())).thenReturn(obj);
        assertTrue(manager.exist("joni"));
    }

    @Test
    @DisplayName("should return false if employee doesn't exist")
    void exist1() {
        when(dao.getByLogin(anyString())).thenReturn(null);
        assertFalse(manager.exist("joni"));
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
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getRoles().get(0).toUpperCase(), obj.getRoles().get(0).toString())
        );
    }

    @Test
    @DisplayName("should throw an error if invalid role")
    void convertRoleFromDtoToEntity() {
        List<String> roles = dto.getRoles();
        roles.add("pastor");
        assertThrows(InvalidDtoException.class, () -> manager.convertRoleFromDtoToEntity(roles));
    }

    @Test
    @DisplayName("should convert entity to dto")
    void convertRoleFromEntityToDto() {
        List<Role> roles = obj.getRoles();
        assertEquals("MANAGER", manager.convertRoleFromEntityToDto(roles).get(0).toString());
    }

    @Test
    @DisplayName("should convert entity to dto")
    void convertRoleFromEntityToDto1() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MANAGER);
        assertEquals("MANAGER", manager.convertRoleFromEntityToDto(roles).get(0));
    }

    @Test
    @DisplayName("should return an employee")
    void getEmployeeByToken() {
        when(dao.getByToken(anyString())).thenReturn(obj);
        assertNotNull(manager.getByToken("jik"));
    }

    @Test
    @DisplayName("should return null")
    void getEmployeeByToken1() {
        when(dao.getByToken(anyString())).thenReturn(null);
        assertNull(manager.getByToken("jik"));
    }

    @Test
    @DisplayName("should return an employee")
    void getEmployeeDtoByToken() {
        when(dao.getByToken(anyString())).thenReturn(obj);
        assertNotNull(manager.getEmployeeDtoByToken("jik"));
    }

    @Test
    @DisplayName("should return null")
    void getEmployeeDtoByToken1() {
        when(dao.getByToken(anyString())).thenReturn(null);
        assertNull(manager.getEmployeeDtoByToken("jik"));
    }

    @Test
    @DisplayName("should return an employee")
    void getEmployeeDtoByLogin() {
        when(dao.getByLogin(anyString())).thenReturn(obj);
        assertNotNull(manager.getEmployeeDtoByLogin("jik"));
    }

    @Test
    @DisplayName("should return null")
    void getEmployeeDtoByLogin1() {
        when(dao.getByLogin(anyString())).thenReturn(null);
        assertNull(manager.getEmployeeDtoByLogin("jik"));
    }

    @Test
    @DisplayName("should return null")
    void getEmployeeByLogin() {
        when(dao.getByLogin(anyString())).thenReturn(null);
        assertNull(manager.getEmployeeByLogin("jik"));
    }

    @Test
    @DisplayName("should return employee")
    void getEmployeeByLogin1() {
        when(dao.getByLogin(anyString())).thenReturn(obj);
        assertNotNull(manager.getEmployeeByLogin("jik"));
    }

    @Test
    @DisplayName("should return an exception if login already exist")
    void checkAndPassLogin() {
        Employee emp = Employee.builder().id(32).firstName("john").lastName("covach").phoneNumber("3242423344533").roles(roles).login(loginDto).password("dedefgrre").build();
        when(dao.getByLogin(anyString())).thenReturn(emp);
        assertThrows(InvalidDtoException.class, () -> manager.checkAndPassLogin(obj, "theo"));

    }

    @Test
    @DisplayName("should not return an exception if id = 0")
    void checkAndPassLogin1() {
        Employee emp = Employee.builder().id(32).firstName("john").lastName("covach").phoneNumber("3242423344533").roles(roles).login(loginDto).password("dedefgrre").build();
        when(dao.getByLogin(anyString())).thenReturn(emp);
        obj.setId(0);
        assertDoesNotThrow(() -> manager.checkAndPassLogin(obj, "theo"));

    }

    @Test
    @DisplayName("should not return an exception if login not used")
    void checkAndPassLogin2() {
        when(dao.getByLogin(anyString())).thenReturn(null);
        assertDoesNotThrow(() -> manager.checkAndPassLogin(obj, "theo"));

    }


    @Test
    @DisplayName("should throw an exception if roleList empty")
    void checkRoleValuesValid() {
        assertThrows(InvalidDtoException.class, () -> manager.checkRoleValuesValid(new ArrayList<>()));
    }

    @Test
    @DisplayName("should throw an exception if role null")
    void checkRoleValuesValid1() {
        List<RoleListDTO> listDTOS = new ArrayList<>();
        listDTOS.add(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkRoleValuesValid(listDTOS));
    }

    @Test
    @DisplayName("should throw an exception if role invalid")
    void checkRoleValuesValid2() {
        List<RoleListDTO> listDTOS = new ArrayList<>();
        RoleListDTO roleListDTO = RoleListDTO.builder().role("plongeur").build();
        RoleListDTO roleListDTO1 = RoleListDTO.builder().role("manager").build();
        listDTOS.add(roleListDTO);
        listDTOS.add(roleListDTO1);
        assertThrows(InvalidDtoException.class, () -> manager.checkRoleValuesValid(listDTOS));
    }

    @Test
    @DisplayName("should not throw an exception if roles are valid")
    void checkRoleValuesValid3() {
        List<RoleListDTO> listDTOS = new ArrayList<>();
        RoleListDTO roleListDTO = RoleListDTO.builder().role("manager").build();
        listDTOS.add(roleListDTO);
        assertDoesNotThrow(() -> manager.checkRoleValuesValid(listDTOS));
    }

    @Test
    @DisplayName("should return an empty list")
    void getByRoles() throws Exception {
        List<RoleListDTO> listDTOS = new ArrayList<>();
        RoleListDTO roleListDTO = RoleListDTO.builder().role("manager").build();
        listDTOS.add(roleListDTO);
        when(dao.getByRole(anyList())).thenReturn(new ArrayList<>());
        assertEquals(0, manager.getByRoles(listDTOS).size());

    }

    @Test
    @DisplayName("should return a list")
    void getByRoles1() throws Exception {
        List<RoleListDTO> listDTOS = new ArrayList<>();
        RoleListDTO roleListDTO = RoleListDTO.builder().role("manager").build();
        listDTOS.add(roleListDTO);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(obj);
        when(dao.getByRole(anyList())).thenReturn(employeeList);
        List<EmployeeDTO> returnedList = manager.getByRoles(listDTOS);
        assertAll(
                () -> assertEquals(dto.getFirstName(), returnedList.get(0).getFirstName().toUpperCase()),
                () -> assertEquals(dto.getLastName(), returnedList.get(0).getLastName().toUpperCase()),
                () -> assertEquals(dto.getPhoneNumber(), returnedList.get(0).getPhoneNumber().toUpperCase()),
                () -> assertEquals(dto.getRoles().get(0).toUpperCase(), returnedList.get(0).getRoles().get(0))
        );

    }

    @Test
    @DisplayName("should insert employee if valid and add date if startDate from dto is null")
    void transferInsert() {
        when(dao.getByLogin(anyString())).thenReturn(null);
        obj = (Employee) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getLogin().toUpperCase(), obj.getLogin()),
                () -> assertNotNull(obj.getStartDate()),
                () -> assertEquals(dto.getRoles().get(0).toUpperCase(), obj.getRoles().get(0).toString())
        );

    }

    @Test
    @DisplayName("should insert employee if valid and transfer startDate if startDate from dto is not null")
    void transferInsert1() {
        Date date = new Date();
        dto.setStartDate(date);
        when(dao.getByLogin(anyString())).thenReturn(null);
        obj = (Employee) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getLogin().toUpperCase(), obj.getLogin()),
                () -> assertEquals(date, obj.getStartDate()),
                () -> assertEquals(dto.getRoles().get(0).toUpperCase(), obj.getRoles().get(0).toString())
        );

    }

    @Test
    @DisplayName("should throw an exception if login already in use")
    void transferInsert2() {
        Employee employee1 = Employee.builder().id(id).firstName(firstName).lastName(lastName).phoneNumber(phoneNumber).login(login).password(password).roles(roles).build();
        when(dao.getByLogin(anyString())).thenReturn(employee1);
        assertThrows(InvalidDtoException.class, () -> manager.transferInsert(dto));

    }

    @Test
    @DisplayName("should throw an exception if id = 0 and registration empty")
    void transferUpdate() {
        dto.setId(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));
    }

    @Test
    @DisplayName("should return null if employee is null")
    void transferUpdate1() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertNull(manager.transferUpdate(dto));
    }

    @Test
    @DisplayName("should return employee")
    void transferUpdate2() {
        dto.setStartDate(new Date());
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = (Employee) manager.transferUpdate(dto);
        System.out.println(obj);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getLogin().toUpperCase(), obj.getLogin()),
                () -> assertEquals(dto.getLastConnection(), obj.getLastConnection()),
                () -> assertEquals(dto.getRoles().get(0).toUpperCase(), obj.getRoles().get(0).toString())
        );
    }

    @Test
    @DisplayName("should not update")
    void transferUpdate3() {
        dto.setFirstName(null);
        dto.setLastName(null);
        dto.setPhoneNumber(null);
        dto.setLogin(null);
        dto.setPassword(null);
        dto.setStartDate(null);
        dto.setRoles(null);
        when(dao.getById(anyInt())).thenReturn(obj);
        manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(obj.getFirstName(), obj.getFirstName()),
                () -> assertEquals(obj.getLastName(), obj.getLastName()),
                () -> assertEquals(obj.getPhoneNumber(), obj.getPhoneNumber()),
                () -> assertEquals(obj.getPassword(), obj.getPassword()),
                () -> assertEquals(obj.getLogin(), obj.getLogin()),
                () -> assertEquals(obj.getStartDate(), obj.getStartDate())
        );

    }
   @Test
   @DisplayName("should throw an exception")
   void deleteById() {
       when(dao.getById(anyInt())).thenReturn(null);
       assertThrows(InvalidDtoException.class, ()-> manager.deleteById(2));

   }

    @Test
    @DisplayName("should return true")
    void deleteById1() {
       obj.getRoles().add(Role.SUPERADMIN);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, ()-> manager.deleteById(2));

    }

    @Test
    @DisplayName("should return false")
    void deleteById2() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertTrue(manager.deleteById(2));

    }

}
