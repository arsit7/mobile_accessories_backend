package com.mobile.accessorie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobile.accessorie.model.User;
import com.mobile.accessorie.repostory.UserRepostory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepostory repostory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final User user = repostory.findByEmail(username);
		return user;

	}

}
