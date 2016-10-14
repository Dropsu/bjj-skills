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
		Technique technique = 
				new Technique.TechniqueBuilder(
						input.getName()).
						desc(input.getDesc()).
						links(input.getLinks()).
						imgLink(input.getImgLink()).
						startingPos(input.getStartingPos()).
						finalPos(input.getFinalPos()).
						submitting(input.getSubmitting()).
						lvlOfCompetence(input.getLvlOfCompetence()).
						build();
		tDAO.addTechnique(technique);
		return technique.getName()+" "+technique.getDesc()+" "+technique.getStartingPos();
	}

}
