package com.ds.domain;

import java.util.ArrayList;

import javax.persistence.*;





@Entity
@Table(name="Technique")
public class Technique {

	@Id @GeneratedValue
	@Column(name= "id")
	private int id;
	private String name;
	private String desc;
	private String links; // TODO: CHANGE TO LIST OF LINKS
	private String imgLink;
	private String startingPos;
	private String finalPos;
	private Boolean submitting;
	private int lvlOfCompetence;
	
	public Technique() {}
	
	// TODO: use builder pattern to create this with varying number of arguments
	
	public Technique(String name) { 
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getStartingPos() {
		return startingPos;
	}

	public void setStartingPos(String startingPos) {
		this.startingPos = startingPos;
	}

	public String getFinalPos() {
		return finalPos;
	}

	public void setFinalPos(String finalPos) {
		this.finalPos = finalPos;
	}

	public int getLvlOfCompetence() {
		return lvlOfCompetence;
	}

	public void setLvlOfCompetence(int lvlOfCompetence2) {
		this.lvlOfCompetence = lvlOfCompetence2;
	}



	public Boolean getSubmitting() {
		return submitting;
	}



	public void setSubmitting(Boolean submitting) {
		this.submitting = submitting;
	}
	
}
