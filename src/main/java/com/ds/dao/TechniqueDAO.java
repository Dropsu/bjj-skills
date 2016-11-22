package com.ds.dao;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ds.domain.Account;
import com.ds.domain.Link;
import com.ds.domain.Technique;

@Repository
@DependsOn("databaseConn")
public class TechniqueDAO {
		
   private static SessionFactory factory; 
   private static ServiceRegistry serviceRegistry;
    
   public TechniqueDAO () {
	  System.out.println("technique DAO created!");
	  TechniqueDAO.factory = DatabaseConn.getFactory();
	  TechniqueDAO.serviceRegistry = DatabaseConn.getServiceRegistry();
   }
 
   public Integer addTechnique(Technique technique, long accountId){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer techniqueID = null;
      try{
         tx = session.beginTransaction();
         Account account = (Account)session.get(Account.class, accountId);
         technique.setAccount(account);
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

   
/*   public List<Technique> listTechniques( ){
      Session session = factory.openSession();
      Transaction tx = null;
      List techniques = null;
      try{
         tx = session.beginTransaction();
         techniques = session.createQuery("FROM Technique").list(); 
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
	return techniques;
   }*/
   
   
   public void updateTechnique(Integer TechniqueID, Technique updatedTechnique){
      Session session = factory.openSession();
      Transaction tx = null;
      Transaction tx2 = null;
      try{
         tx = session.beginTransaction();
         Technique technique = 
                    (Technique)session.get(Technique.class, TechniqueID);
         tx.commit();
         updatedTechnique.setAccount(technique.getAccount());
         technique.update(updatedTechnique);
         tx2 = session.beginTransaction();
         session.update(technique);
         tx2.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
   public Technique getTechnique(Integer TechniqueID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Technique technique = null;
	      try{
	         tx = session.beginTransaction();
	          technique = 
	                    (Technique)session.get(Technique.class, TechniqueID);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	     
	      return technique;
	   }
   
   public Set<Technique> getTechniques(Long AccountId){
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
	     
	      return account.getTechniques();
	   }

   public void deleteTechnique(Integer TechniqueID){ // TODO: Now it delates technique with associated account (and it's techniques), (coz of casdade?)
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