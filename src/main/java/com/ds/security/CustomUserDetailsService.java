package com.ds.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ds.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.domain.Account;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service("customUserDetailsService")
public class CustomUserDetailsService extends Account implements UserDetailsService {

    @Autowired
	private AccountService accService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accService.getAccountByUsername(username);
		System.out.println(account.getPassword());
        return new User(account.getUsername()
                ,account.getPassword(),true,true,true,true,getGrantedAuthorities(account));
	}

    private List<GrantedAuthority> getGrantedAuthorities(Account account){
        List<GrantedAuthority> authorities = new ArrayList<>();

        /*for(UserProfile userProfile : account.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile); TODO: add possibility of multiple roles */
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            
        return authorities;
    }

    public static boolean checkAuthorization (Principal principal, String validUsername) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority sga: authorities
             ) {
            if(sga.getAuthority().equals("ROLE_ADMIN")){
                return true;
            }
        }
        if (principal.getName().equals(validUsername)) {
            return true;
        }
        return false;
    }

}
