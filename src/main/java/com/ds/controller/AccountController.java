package com.ds.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.ds.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.domain.Account;
import com.ds.service.AccountService;

import java.security.Principal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accService;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add (@RequestBody Account input) {
		int id = accService.addAccount(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(AccountController.class).slash(input.getUsername()).toUri());//TODO: replace slash with pointer to some method on account
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{userName}",method = RequestMethod.DELETE)
	ResponseEntity<?> delete (@PathVariable String userName, Principal principal) throws Exception {
        if(!CustomUserDetailsService.checkAuthorization(principal,userName)) {
            throw new Exception("Access denied");//TODO: Nicer exception handling, error page
        }
        if(principal.getName().equals(userName)){
            SecurityContextHolder.clearContext();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        int addId = accService.getAccountByUsername(userName).getId();
		accService.deleteAccount(addId);
        return new ResponseEntity<>(null, httpHeaders ,HttpStatus.MULTI_STATUS.GONE);
	}

}
