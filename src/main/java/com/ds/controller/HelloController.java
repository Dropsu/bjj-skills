package com.ds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class HelloController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello(Principal principal) {
		return "<h1>Welcome to bjj-skills "+principal.getName()+"</h1>";
	}

}
