package com.ds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/{accountId}", method = RequestMethod.POST)
	Integer add(@PathVariable long accountId, @RequestBody Technique input) {
		System.out.println(accountId);
		return tDAO.addTechnique(input,accountId);
	}
	
	//TODO: Updating techniques
	
	/*@RequestMapping(value="/update/{techniqueId}", method = RequestMethod.POST)
	String update(@PathVariable Integer techniqueId, @RequestBody Technique input) {
		tDAO.updateTechnique(techniqueId, input);
		return "ok";
	}*/
	
	@RequestMapping(value="/delete/{techniqueId}", method = RequestMethod.DELETE)
	String delete(@PathVariable Integer techniqueId) {
		tDAO.deleteTechnique(techniqueId);
		return "ok";
	}

}
