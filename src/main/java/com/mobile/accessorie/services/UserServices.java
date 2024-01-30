package com.mobile.accessorie.services;

import com.mobile.accessorie.model.JwtResponse;

import com.mobile.accessorie.model.User;

public interface UserServices {

	User addUser(User user) throws Exception;

	JwtResponse loginUser(User user);

}
