package com.ds.database;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("databaseConn")
public class AccountDAO {

	public AccountDAO () {
		System.out.println("acc DAO created");
	}
}
