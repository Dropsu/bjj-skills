package com.ds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.database.AccountDAO;
import com.ds.domain.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountDAO accDAO;
	
	void add (@RequestBody Account account) {
		
	}

}
