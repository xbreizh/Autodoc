package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class EmployeeDaoImpl extends AbstractHibernateDao implements EmployeeDao {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class);
    private Class<?> cl = Employee.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {

        return Employee.getSearchField();
    }

    @Override
    public Employee getByLogin(String login) {
        TypedQuery<Employee> query = getCurrentSession().createQuery(FROM + " Employee where login= :login");
        query.setParameter("login", login.toUpperCase());
        List<Employee> employees = query.getResultList();
        LOGGER.info("size in dao: " + employees.size());
        if (!employees.isEmpty()) return employees.get(0);
        return null;

    }

    @Override
    public Employee getByToken(String token) {
        LOGGER.debug("token: " + token);
        TypedQuery<Employee> query = getCurrentSession().createQuery(FROM + " Employee where token= :token");
        query.setParameter("token", token);
        List<Employee> employees = query.getResultList();
        LOGGER.debug("found: " + employees.size());
        if (!employees.isEmpty()) return employees.get(0);
        return null;
    }


    @Override
    public List<Employee> getByRole(List<Role> roles) {
        LOGGER.info("roles received: " + roles);
        if (roles == null) return new ArrayList<>();
        if (roles.isEmpty()) return new ArrayList<>();
        String init = "select * from employee where  ";
        StringBuilder sb = new StringBuilder(init);
        for (Role role : roles) {
            String condition = "id in (select employee_id from employee_roles where roles like '%" + role.toString().toUpperCase() + "%')";
            if (!sb.toString().equals(init)) {
                sb.append(" and ");
            }
            sb.append(condition);
        }
        LOGGER.info("query so far: " + sb);
        TypedQuery<Employee> query = getCurrentSession().createNativeQuery(sb.toString(), Employee.class);


        return query.getResultList();
    }


    public boolean deleteById(int entityId) {
        LOGGER.info("trying to delete element with id: " + entityId);
        Employee entity = getCurrentSession().get(Employee.class, entityId);
        entity.setRoles(null);
        getCurrentSession().update(entity);
        getCurrentSession().delete(entity);
        return true;
    }

}
