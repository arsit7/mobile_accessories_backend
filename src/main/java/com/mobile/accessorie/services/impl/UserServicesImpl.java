package com.mobile.accessorie.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		} catch (DuplicateEmailException e) {
			log.info("Faild to Register User : {}", e.getMessage());

			throw e;
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

	@Override
	public User UpdateUser(User user) {
		try {
			User updateuser = this.userRepostory.findByEmail(user.getEmail());
			if (updateuser == null) {
				throw new UsernameNotFoundException("User with email " + user.getEmail() + "not found");
			}
			updateuser.setFristName(user.getFristName());
			updateuser.setLastname(user.getLastname());
			updateuser.setEmail(user.getEmail());
			updateuser.setPassword(user.getPassword());

			// Encode the password before saving
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				updateuser.setPassword(encodedPassword);

			}

			// Save the user with the encoded password

			User save = this.userRepostory.save(updateuser);
			save.setPassword(user.getPassword());

			return save;
		} catch (UsernameNotFoundException e) {
			// Log and re-throw the exception
			log.info("Failed to update user: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public User getUserSingle(User user) {

		User findByEmail = this.userRepostory.findByEmail(user.getEmail());

		return findByEmail;
	}

	@Override
	public List<User> getallUsers() {

		List<User> list = this.userRepostory.findAll();

		List<User> collect = list.stream().map(user -> (user)).collect(Collectors.toList());
		return collect;

	}

	@Override
	public void deleteUser(Integer userId) {

		Optional<User> user = this.userRepostory.findById(userId);

		this.userRepostory.delete(user.get());

	}

}
