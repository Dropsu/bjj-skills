package com.ds.database;
import java.util.List; 
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import com.ds.domain.Account;
import com.ds.domain.Link;
import com.ds.domain.Technique;

@Service
public class TechniqueDAO {
	
   private static SessionFactory factory; 
   private static ServiceRegistry serviceRegistry;
    
   public TechniqueDAO () {
	   try{
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
 
   public Integer addTechnique(Technique technique){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer techniqueID = null;
      try{
         tx = session.beginTransaction();
         techniqueID = (Integer) session.save(technique); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return techniqueID;
   }

   
   public void listTechniques( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List techniques = session.createQuery("FROM Technique").list(); 
         for (Iterator iterator = 
                           techniques.iterator(); iterator.hasNext();){
            Technique technique = (Technique) iterator.next(); 
            System.out.print("Technique: " + technique.getName()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
   public void updateTechnique(Integer TechniqueID, int lvlOfCompetence ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Technique technique = 
                    (Technique)session.get(Technique.class, TechniqueID); 
         technique.setLvlOfCompetence(lvlOfCompetence);
		 session.update(technique); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }

   public void deleteTechnique(Integer TechniqueID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Technique technique = 
                   (Technique)session.get(Technique.class, TechniqueID); 
         session.delete(technique); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}