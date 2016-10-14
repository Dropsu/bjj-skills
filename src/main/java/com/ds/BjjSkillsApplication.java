package com.ds;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ds.database.DatabaseConn;
import com.ds.database.TechniqueDAO;


@SpringBootApplication
public class BjjSkillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BjjSkillsApplication.class, args);
	}
	
}
