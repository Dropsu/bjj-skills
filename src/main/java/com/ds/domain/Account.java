package com.ds.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Account {

	@Id @GeneratedValue
	@Column
	private long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private Set<Technique> techniques;

	private Account() { }

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
	
	
	
	
	
}
