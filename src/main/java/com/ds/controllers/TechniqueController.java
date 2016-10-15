package com.ds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.database.TechniqueDAO;
import com.ds.domain.Account;
import com.ds.domain.Technique;

@RestController
@RequestMapping("/techniques")
public class TechniqueController {
	
	@Autowired
	TechniqueDAO tDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	String add(@RequestBody Technique input) {
		tDAO.addTechnique(input);
		return "ok";
	}

}
