package com.ds.service;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.ds.dao.TechniqueDAO;
import com.ds.domain.Account;
import com.ds.domain.Technique;

@Service
@DependsOn("techniqueDAO")
public class TechniqueService {

	@Autowired
	TechniqueDAO techDAO;
	
	 public Integer addTechnique(Technique technique, long accountId){
	   return techDAO.addTechnique(technique, (int) accountId);
	   }
	 
	 public void updateTechnique(Integer TechniqueID, Technique updatedTechnique){
	      techDAO.updateTechnique(TechniqueID, updatedTechnique);
	   }
	 
	 public Technique getTechnique(Integer TechniqueID){
	      return techDAO.getTechnique(TechniqueID);
	   }
	 
	 public Set<Technique> getTechniques(Integer AccountId){
	     return techDAO.getTechniques(AccountId);
	   }
	 
	 public void deleteTechnique(Integer TechniqueID){
	      techDAO.deleteTechnique(TechniqueID);
	   }
	
}
