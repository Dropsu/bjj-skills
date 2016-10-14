package com.ds.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Account {

	@Id @GeneratedValue
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
	
	
	
	
}
