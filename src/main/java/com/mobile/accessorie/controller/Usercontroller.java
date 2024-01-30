package com.mobile.accessorie.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.accessorie.model.JwtResponse;
import com.mobile.accessorie.model.User;
import com.mobile.accessorie.services.UserServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class Usercontroller {

	@Autowired
	private UserServices userServices;

	@PostMapping("/save")
	public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
		User save = userServices.addUser(user);
		return new ResponseEntity<>(save, (null == save) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody User user) {
		JwtResponse jwtResponse = userServices.loginUser(user);
		return new ResponseEntity<>(jwtResponse,
				(null == jwtResponse) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK);
	}

}
