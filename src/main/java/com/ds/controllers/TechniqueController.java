package com.ds.controllers;

import javax.transaction.Transactional;

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

import linkwrappers.TechniqueLinkWrapper;

@Transactional
@RestController
@RequestMapping("/techniques")
public class TechniqueController {
	
	@Autowired
	TechniqueDAO tDAO;
	
	@RequestMapping(value="/{accountId}", method = RequestMethod.POST)
	ResponseEntity<?> Integer (@PathVariable long accountId, @RequestBody Technique input) {
		System.out.println(accountId);
		long id = tDAO.addTechnique(input,accountId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/techniques/"+id)
				.buildAndExpand(id).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.GET)
	public
	TechniqueLinkWrapper get(@PathVariable Integer techniqueId) {
		Technique technique = tDAO.getTechnique(techniqueId);
		return new TechniqueLinkWrapper(technique);				
	}
	

	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.PUT)
	String update(@PathVariable Integer techniqueId, @RequestBody Technique input) {
		tDAO.updateTechnique(techniqueId, input);
		return "ok";
	}
	
	@RequestMapping(value="/delete/{techniqueId}", method = RequestMethod.DELETE)
	String delete(@PathVariable Integer techniqueId) {
		tDAO.deleteTechnique(techniqueId);
		return "ok";
	} 

}
