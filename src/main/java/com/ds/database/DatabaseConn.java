package com.ds.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import com.ds.domain.Account;
import com.ds.domain.Link;
import com.ds.domain.Technique;

@Service
public class DatabaseConn {

	private static SessionFactory factory; 
	private static ServiceRegistry serviceRegistry;
	
	public DatabaseConn () {
		 try{		   
			 System.out.println("databaseconn created!");
	    	  Configuration configuration = new Configuration().
	       		   addPackage("com.ds.domain").
	                  addAnnotatedClass(Technique.class).
	                  addAnnotatedClass(Link.class).
	                  addAnnotatedClass(Account.class).
	                  configure().
	                  setProperty("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
	                  serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	                          configuration.getProperties()).build();
	                  factory = configuration.buildSessionFactory(serviceRegistry);  
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}

	public static SessionFactory getFactory() {
		return factory;
	}

	public static ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	
	
	
}
