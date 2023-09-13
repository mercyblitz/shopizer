package com.salesmanager.shop.commons.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service("socialCustomerDetailsService")
public class SocialCustomerServicesImpl implements UserDetailsService{
	
	@Inject
	UserDetailsService customerDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//delegates to Customer fetch service
		UserDetails userDetails =  customerDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
        	return null;
        }
        
        return userDetails;
	}

}
