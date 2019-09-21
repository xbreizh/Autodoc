package com.autodoc.model;

import javax.persistence.*;

@Entity(name = "example_object")
public class ExampleObject
{
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	private String name;

	public ExampleObject()
	{
		setName("");
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		String string = "{";
		string += "\"id\": " + getId() + ", ";
		string += "\"name\": \"" + getName() + "\"";
		string += "}";
		return string;
	}
}
