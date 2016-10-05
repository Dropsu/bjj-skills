package com.ds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ds.database.DatabaseTest;
import com.ds.database.ManageEmployee;
import com.ds.database.ManageEmployeeXML;
import com.ds.database.ManageEmployeeXML;
import com.ds.database.TechniqueDAO;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "Czesc DamiX";
	}
	
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	@ResponseBody
	public String dbTest() throws Exception {
		return DatabaseTest.dbTaste();
	}
	
	@RequestMapping(value = "/hibernate", method = RequestMethod.GET)
	@ResponseBody
	public void hibernateTest() throws Exception {

		ManageEmployee.testAnnotations();
	}

}
