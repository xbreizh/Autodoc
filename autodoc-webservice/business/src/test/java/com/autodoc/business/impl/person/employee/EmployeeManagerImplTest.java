package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeManagerImplTest {


    private EmployeeManager employeeManager;
    private EmployeeDaoImpl employeeDao;


    @BeforeEach
    void init() {
        employeeDao = mock(EmployeeDaoImpl.class);
        employeeManager = new EmployeeManagerImpl<>(employeeDao);
    }

    @Test
    void getAll() {
        List<Employee> list = new ArrayList<>();
        when(employeeDao.getAll()).thenReturn(list);
        assertNotNull(employeeManager.getAll());
    }

  /*  @Test
    void update() {
        EmployeeDTO dto = new EmployeeDTO();
        int id = 2;
        dto.setLogin("MALEK");
        Employee employee = employeeDao.getById(id)
        employeeDao.update(employee);
        when(employeeDao.getAll()).thenReturn(list);
        assertNotNull(employeeManager.getAll());
    }*/


/*    @Test
    @DisplayName("should not return exception if valid roles")
    void checkRoleValuesValid() {
        String[] rolesArray = {"MANAGER", "Mecanic"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertDoesNotThrow(
                ()->employeeManager.checkRoleValuesValid(rolesToCheck));

    }

    @Test
    @DisplayName("should return exception if invalid roles")
    void checkRoleValuesValid1() {
        String[] rolesArray = {"MANAGER", "Pharmacist"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertThrows(Exception.class,
                ()->employeeManager.checkRoleValuesValid(rolesToCheck));

    }*/

    @Test
    @DisplayName("should return true if dao returns true")
    void deleteById() throws Exception {

        when(employeeDao.deleteById(anyInt())).thenReturn(true);
        assertTrue(employeeManager.deleteById(3));
    }

    @Test
    @DisplayName("should return false if dao returns false")
    void deleteById1() throws Exception {

        when(employeeDao.deleteById(anyInt())).thenReturn(false);
        assertFalse(employeeManager.deleteById(3));
    }

    @Test
    @DisplayName("should throw an exception is roles empty")
    void getByRoles() throws Exception {
        List<RoleListDTO> roleList = new ArrayList<>();
        RoleListDTO roleListDTO = new RoleListDTO();
        roleListDTO.setRole("role");
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(new EmployeeDTO());
        when(employeeDao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> employeeManager.getByRoles(roleList));
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
        when(employeeDao.getByRole(anyList())).thenReturn(list);
        assertEquals(login, employeeManager.getByRoles(roleList).get(0).getLogin());
    }

    @Test
    @DisplayName("should throw an exception is roles has no name")
    void getByRoles1() throws Exception {
        List<RoleListDTO> roleList = new ArrayList<>();
        roleList.add(new RoleListDTO());
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(new EmployeeDTO());
        when(employeeDao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> employeeManager.getByRoles(roleList));
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
        when(employeeDao.getByRole(anyList())).thenReturn(list);
        assertThrows(Exception.class, () -> employeeManager.getByRoles(roleList));
    }

    @Test
    void getByLogin() {
        String login = "Bob56";
        Employee employee = new Employee();
        employee.setLogin(login);
        when(employeeDao.getByLogin(anyString())).thenReturn(employee);
        assertEquals(login, employeeManager.getEmployeeByLogin(login).getLogin());
    }
}