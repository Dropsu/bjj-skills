package com.ds.domain;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="Technique")
public class Technique {

	@Id @GeneratedValue
	@Column
	private int id;
	
	@ManyToOne
	private Account account;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "technique")
	private Set <Link> links; 
	
	@Column(name = "img_link")
	private String imgLink;
	
	@Column(name = "starting_pos")
	private String startingPos;
	
	@Column(name = "final_pos")
	private String finalPos;
	
	@Column
	private Boolean submitting;
	
	@Column(name = "lvl_of_competence")
	private int lvlOfCompetence;
	
	public Technique() {}
	
	// TODO: use builder pattern to create this with varying number of arguments
	

	private Technique(TechniqueBuilder builder) {
		super();
		this.name = builder.name;
		this.description = builder.desc;
		this.links = builder.links;
		this.imgLink = builder.imgLink;
		this.startingPos = builder.startingPos;
		this.finalPos = builder.finalPos;
		this.submitting = builder.submitting;
		this.lvlOfCompetence = builder.lvlOfCompetence;
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
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
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
	
	public static class TechniqueBuilder {

		
		private String name;
		private String desc;
		private Set <Link> links; 
		private String imgLink;
		private String startingPos;
		private String finalPos;
		private Boolean submitting;
		private int lvlOfCompetence;
		
		public TechniqueBuilder(String name) {
			this.name = name;
		}

		public TechniqueBuilder desc(String desc) {
			this.desc = desc;
			return this;
		}
		
		public TechniqueBuilder links(Set<Link> links) {
			this.links = links;
			return this;
		}

		public TechniqueBuilder imgLink(String imgLink) {
			this.imgLink = imgLink;
			return this;
		}

		public TechniqueBuilder startingPos(String startingPos) {
			this.startingPos = startingPos;
			return this;
		}

		public TechniqueBuilder finalPos(String finalPos) {
			this.finalPos = finalPos;
			return this;
		}

		public TechniqueBuilder submitting(Boolean submitting) {
			this.submitting = submitting;
			return this;
		}

		public TechniqueBuilder lvlOfCompetence(int lvlOfCompetence) {
			this.lvlOfCompetence = lvlOfCompetence;
			return this;
		}
		
		public Technique build () {
			return new Technique (this);
		}

	}
	
}
