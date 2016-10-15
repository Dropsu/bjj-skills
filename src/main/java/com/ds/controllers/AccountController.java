package com.ds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.database.AccountDAO;
import com.ds.domain.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountDAO accDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	Long add (@RequestBody Account input) {
		return accDAO.addAccount(input);
	}

}
