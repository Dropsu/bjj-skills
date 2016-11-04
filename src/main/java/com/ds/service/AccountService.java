package com.ds.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	
	public Long addAccount(Account account){
	       return accDAO.addAccount(account);
	   }
	
	 public Account getAccountByUsername(String username){
	    return accDAO.getAccountByUsername(username);
	   }
	
	
}
