package com.ds.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashSet;
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

import com.ds.database.AccountDAO;
import com.ds.domain.Account;
import com.ds.domain.Technique;

import linkwrappers.AccountLinkWrapper;
import linkwrappers.TechniqueLinkWrapper;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountDAO accDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@RequestBody Account input) {
		long id = accDAO.addAccount(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(AccountController.class).slash(id).toUri());//TODO: replace slash with pointer to some method on account
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

}
