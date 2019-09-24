package com.autodoc.dao.impl;


import com.autodoc.model.models.ExampleObject;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class ExampleDAO
{
	private SessionFactory sessionFactory;

	public ExampleDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<ExampleObject> get()
	{
		return sessionFactory.getCurrentSession().
				createQuery("from example_object").list();
	}

	@Transactional
	public ExampleObject get(String id)
	{
		ExampleObject object =
				(ExampleObject) sessionFactory.getCurrentSession().
						load(ExampleObject.class, Long.valueOf(id));
		Hibernate.initialize(object);
		return object;
	}

	@Transactional
	public void add(ExampleObject object)
	{
		if (object != null)
		{
			sessionFactory.getCurrentSession().save(object);
		}
	}
}
