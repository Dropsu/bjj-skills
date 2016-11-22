package com.ds.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ds.service.AccountService;
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

import com.ds.dao.TechniqueDAO;
import com.ds.domain.Account;
import com.ds.domain.Technique;
import com.ds.service.TechniqueService;

import hateoas.TechniqueLinkWrapper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/accounts/{accountUsername}/techniques")
public class TechniqueController {
		
	@Autowired
	TechniqueService techService;

	@Autowired
	AccountService accService;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@PathVariable String accountUsername, @RequestBody Technique input) {
		long accountId = accService.getAccountByUsername(accountUsername).getId();
		Integer id = techService.addTechnique(input,accountId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(methodOn(TechniqueController.class).get(accountUsername,id)).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.GET)
	public
	TechniqueLinkWrapper get(@PathVariable String accountUsername, @PathVariable Integer techniqueId) {
		Technique technique = techService.getTechnique(techniqueId);
		return new TechniqueLinkWrapper(technique);				
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public
	Set<TechniqueLinkWrapper> getTechniquesList(@PathVariable String accountUsername) {
		long accountId = accService.getAccountByUsername(accountUsername).getId();
		Set <Technique> techniques = techService.getTechniques(accountId);
		Set<TechniqueLinkWrapper> techniquesWithLinks = new HashSet<TechniqueLinkWrapper>();
		techniques.forEach(t ->{
			techniquesWithLinks.add(new TechniqueLinkWrapper(t));
		});
		return techniquesWithLinks;
				
	}
	

	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.PUT)
	String update(@PathVariable Integer techniqueId, @RequestBody Technique input) {
		techService.updateTechnique(techniqueId, input);
		return "ok";
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.DELETE)
	String delete(@PathVariable Integer techniqueId) {
		techService.deleteTechnique(techniqueId);
		return "ok";
	} 

}
