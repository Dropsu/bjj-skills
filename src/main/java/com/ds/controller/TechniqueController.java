package com.ds.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import com.ds.security.CustomUserDetailsService;
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

import com.ds.domain.Technique;
import com.ds.service.TechniqueService;

import hateoas.LinkWrapper;

@RestController
@RequestMapping("/accounts/{accountUsername}/techniques")
public class TechniqueController {
		
	@Autowired
	TechniqueService techService;

	@Autowired
	AccountService accService;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@PathVariable String accountUsername, @RequestBody Technique input, Principal principal) throws Exception {
        if(!CustomUserDetailsService.checkAuthorization(principal.getName(),accountUsername)) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
        long accountId = accService.getAccountByUsername(accountUsername).getId();
		Integer id = techService.addTechnique(input,accountId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(methodOn(TechniqueController.class).get(accountUsername,id,principal)).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.GET)
	public LinkWrapper get(@PathVariable String accountUsername, @PathVariable Integer techniqueId, Principal principal) throws Exception {
        if(!CustomUserDetailsService.checkAuthorization(principal.getName(),accountUsername)) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
		Technique technique = techService.getTechnique(techniqueId);
        if(!CustomUserDetailsService.checkAuthorization(principal.getName(),technique.getAccount().getUsername())) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
		return new LinkWrapper(technique);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public
	Set<LinkWrapper> getTechniquesList(@PathVariable String accountUsername, Principal principal) throws Exception {
        if(!CustomUserDetailsService.checkAuthorization(principal.getName(),accountUsername)) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
		long accountId = accService.getAccountByUsername(accountUsername).getId();
		Set <Technique> techniques = techService.getTechniques(accountId);
		Set<LinkWrapper> techniquesWithLinks = new HashSet<LinkWrapper>();
		techniques.forEach(t ->{
            try {
                techniquesWithLinks.add(new LinkWrapper(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		return techniquesWithLinks;
				
	}
	

	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.PUT)
	String update(@PathVariable Integer techniqueId, @RequestBody Technique input, Principal principal) throws Exception {
        if(!CustomUserDetailsService.checkAuthorization(principal.getName(),techService.getTechnique(techniqueId).getAccount().getUsername())) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
		techService.updateTechnique(techniqueId, input);
		return "ok";
	}
	
	@RequestMapping(value="/{techniqueId}", method = RequestMethod.DELETE)
	String delete(@PathVariable Integer techniqueId, Principal principal) throws Exception {
       /* if(!CustomUserDetailsService.checkAuthorization(principal.getName(),techService.getTechnique(techniqueId).getAccount().getUsername())) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }*/
		techService.deleteTechnique(techniqueId);
		return "ok";
	} 

}
