package com.ds.database;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.ds.domain.Account;
import com.ds.domain.Technique;

@Service
@DependsOn("databaseConn")
public class AccountDAO {
	
	private static SessionFactory factory; 
	private static ServiceRegistry serviceRegistry;

	public AccountDAO () {
		System.out.println("acc DAO created");
		 AccountDAO.factory = DatabaseConn.getFactory();
		 AccountDAO.serviceRegistry = DatabaseConn.getServiceRegistry();
	}
	
	public Long addAccount(Account account){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Long accID = null;
	      try{
	         tx = session.beginTransaction();
	         accID = (Long) session.save(account); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return accID;
	   }
	
	  public Account getAccount(Long AccountId){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Account account = null;
	      try{
	         tx = session.beginTransaction();
	          account = 
	                    (Account)session.get(Account.class, AccountId);
	          Hibernate.initialize(account.getTechniques());
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	     
	      return account;
	   }
	
}
