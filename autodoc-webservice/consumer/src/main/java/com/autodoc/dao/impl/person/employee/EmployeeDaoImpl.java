package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmployeeDaoImpl<T> extends AbstractHibernateDao implements EmployeeDao {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class);
    private Class cl = Employee.class;

    public EmployeeDaoImpl() {
        this.setClazz(Employee.class);
    }

    public Map<String, SearchType> getSearchField() {

        return Employee.SEARCH_FIELD;
    }

    @Override
    public Employee getByLogin(String login) {
        Query query = getCurrentSession().createQuery("From Employee where login= :login", cl);
        query.setParameter("login", login.toUpperCase());
        List<Employee> employees = query.getResultList();
        LOGGER.debug("found: " + employees.size());
        System.out.println("size in dao: " + employees.size());
        if (!employees.isEmpty()) return employees.get(0);
        return null;

    }

    @Override
    public Employee getByToken(String token) {
        LOGGER.debug("token: " + token);
        Query query = getCurrentSession().createQuery("From Employee where token= :token");
        query.setParameter("token", token);
        List<Employee> employees = (List<Employee>) query.getResultList();
        LOGGER.debug("found: " + employees.size());
        if (!employees.isEmpty()) return employees.get(0);
        return null;
    }


    @Override
    public List<Employee> getByRole(List<Role> roles) {
        System.out.println("roles received: " + roles);
        if (roles == null) return null;
        String init = "select * from employee where  ";
        StringBuilder sb = new StringBuilder(init);
        for (Role role : roles) {
            String condition = "id in (select employee_id from employee_roles where roles like '%" + role + "%')";
            if (!sb.toString().equals(init)) {
                sb.append(" and ");
            }
            sb.append(condition);
        }
        System.out.println("query so far: " + sb);
        Query query = getCurrentSession().createNativeQuery(sb.toString(), Employee.class);


        List<Employee> employees = (List<Employee>) query.getResultList();
        System.out.println("size: " + employees.size());
        LOGGER.debug("found: " + employees.size());
        if (!employees.isEmpty()) return employees;
        return new ArrayList<>();
    }


    public boolean deleteById(int entityId) {
        System.out.println("trying to delete element with id: " + entityId);
        Employee entity = getCurrentSession().get(Employee.class, entityId);
        entity.setRoles(null);
        getCurrentSession().update(entity);
        getCurrentSession().delete(entity);
        return true;
    }

}
