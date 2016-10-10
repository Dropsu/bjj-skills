package com.ds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Link")
public class Link {
	
	@Id@GeneratedValue
	@Column
	private int id;
	
	@Column
	private String link;

	@ManyToOne
	private Technique technique;

	
	public Link() { }


	public Link(String link) {
		super();
		this.link = link;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}

	
	
}
