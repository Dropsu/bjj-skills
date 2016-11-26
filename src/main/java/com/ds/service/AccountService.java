package com.ds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.ds.dao.AccountDAO;
import com.ds.domain.Account;

@Service
@DependsOn("accountDAO")
public class AccountService {

	@Autowired
	private AccountDAO accDAO;
	
	public int addAccount(Account account){
	       return accDAO.addAccount(account);
	   }
	
	 public Account getAccountByUsername(String username){
	    return accDAO.getAccountByUsername(username);
	   }

	   public void deleteAccount (Integer accountID) {
		   accDAO.deleteAccount(accountID);
	   }
	
	
}
