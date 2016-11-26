package com.ds.domain;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Account {

	@Id @GeneratedValue
	@Column
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Technique> techniques;

	public Account() { }

	
	
	public Account(Account acc) {
		this.id = acc.getId();
		this.username = acc.getUsername();
		this.password = acc.getPassword();
		this.techniques = acc.getTechniques();
	}



	private Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Technique> getTechniques() {
		return techniques;
	}

	public void setTechniques(Set<Technique> techniques) {
		this.techniques = techniques;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
}
