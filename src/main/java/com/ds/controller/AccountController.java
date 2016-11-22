package com.ds.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.domain.Account;
import com.ds.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accService;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@RequestBody Account input) {
		long id = accService.addAccount(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(AccountController.class).slash(input.getUsername()).toUri());//TODO: replace slash with pointer to some method on account
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}


	/*@RequestMapping(value= "/{accountUsername}", method = RequestMethod.GET)
	Account getAcc (@PathVariable String accountUsername) {

        return accService.getAccountByUsername(accountUsername);
	}*/

}
