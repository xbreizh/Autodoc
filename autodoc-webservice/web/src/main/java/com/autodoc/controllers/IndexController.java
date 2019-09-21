package com.autodoc.controllers;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.impl.ExampleDAO;
import com.autodoc.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@Controller
@RequestMapping("/")
public class IndexController

{
	@Autowired
	ExampleDAO exampleDAO;

	@Autowired
	private CarManager manager;



	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model)
	{
		//model.addAttribute("message", "Hello World!!!");
		System.out.println("getting here");
		Car car = new Car();
		Random rand = new Random();
		car.setId(rand.nextInt());
		System.out.println("object: "+car);
		car.setRegistration("reg3");
		manager.save(car);

		return "indexPage";
	}
}