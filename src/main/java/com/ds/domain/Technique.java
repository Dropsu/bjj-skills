package com.ds.domain;

import java.util.ArrayList;

public class Technique {

	private int id;
	private String name;
	private String desc;
	private ArrayList <String> links;
	private String imgLink;
	private String startingPos;
	private String finalPos;
	private byte lvlOfCompetence;
	
	public Technique() {}
	
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

	public ArrayList<String> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<String> links) {
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

	public byte getLvlOfCompetence() {
		return lvlOfCompetence;
	}

	public void setLvlOfCompetence(byte lvlOfCompetence) {
		this.lvlOfCompetence = lvlOfCompetence;
	}
	
}
