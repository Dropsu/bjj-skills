package com.ds.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("customUserDetailsService") // ?
    UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable(); //disabled while im testing in postman (token in cookies would work but is insecure) 
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception  {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("dmiq")
		.roles("ADMIN","USER")
		.and()
		.withUser("user")
		.password("qwerty")
		.roles("USER");
	}
	
}
