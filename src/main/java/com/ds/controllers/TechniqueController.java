package com.ds.controllers;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ds.database.TechniqueDAO;
import com.ds.domain.Account;
import com.ds.domain.Technique;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import linkwrappers.TechniqueLinkWrapper;

@RestController
@RequestMapping("/accounts/{accountId}/techniques")
public class TechniqueController {
	
	@Autowired
	TechniqueDAO tDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@PathVariable long accountId, @RequestBody Technique input) {
		Integer id = tDAO.addTechnique(input,accountId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(methodOn(TechniqueController.class).get(accountId,id)).toUri());		
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.GET)
	public
	TechniqueLinkWrapper get(@PathVariable long accountId, @PathVariable Integer techniqueId) {
		Technique technique = tDAO.getTechnique(techniqueId);
		return new TechniqueLinkWrapper(technique);				
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public
	Set<TechniqueLinkWrapper> getTechniquesList(@PathVariable Long accountId) {
		Set <Technique> techniques = tDAO.getTechniques(accountId);
		Set<TechniqueLinkWrapper> techniquesWithLinks = new HashSet<TechniqueLinkWrapper>();
		techniques.forEach(t ->{
			techniquesWithLinks.add(new TechniqueLinkWrapper(t));
		});
		return techniquesWithLinks;
				
	}
	

	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.PUT)
	String update(@PathVariable Integer techniqueId, @RequestBody Technique input) {
		tDAO.updateTechnique(techniqueId, input);
		return "ok";
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.DELETE)
	String delete(@PathVariable Integer techniqueId) {
		tDAO.deleteTechnique(techniqueId);
		return "ok";
	} 

}
