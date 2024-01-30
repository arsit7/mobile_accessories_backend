package com.mobile.accessorie.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobile.accessorie.ExceptionHandling.DuplicateEmailException;
import com.mobile.accessorie.model.JwtResponse;
import com.mobile.accessorie.model.User;
import com.mobile.accessorie.repostory.UserRepostory;
import com.mobile.accessorie.security.JwtHelper;
import com.mobile.accessorie.services.UserServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepostory userRepostory;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) throws Exception {
		try {
			User isExistEmail = userRepostory.findByEmail(user.getEmail());
			if (isExistEmail != null)
				throw new DuplicateEmailException("Email address is already in ");

			// Encode the password before saving

			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);

			// Save the user with the encoded password
			User save = userRepostory.save(user);
			return save;
		} catch (Exception e) {
			log.info("Faild to Register User");
			throw new Exception(e);
		}

	}

	@Override
	public JwtResponse loginUser(User user) {
		try {
			final String token = jwtHelper.generateToken(user);
			JwtResponse jwtResponse = new JwtResponse(token);
			return jwtResponse;
		} catch (Exception e) {
			log.info("Faild to genrate Auth token : {}", e);
			throw e;
		}
	}

}
