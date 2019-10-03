package com.autodoc.dao.impl;

import com.autodoc.dao.contract.EmployeeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.employee.Employee;
import org.hibernate.query.Query;

public class EmployeeDaoImpl extends AbstractHibernateDao implements EmployeeDao {
    @Override
    public Employee getByLogin(String login) {
        Query query = getCurrentSession().createQuery("From Employee where login= :login");
        query.setParameter("login", login);
        return (Employee) query.getSingleResult();
    }

}
