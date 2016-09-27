package com.ds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.database.DatabaseTest;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "Czesc DamiX";
	}
	
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	@ResponseBody
	public String test() throws Exception {
		return DatabaseTest.testDb();
	}
	

}
