package com.mobile.accessorie.services;

import java.util.List;

import com.mobile.accessorie.model.JwtResponse;

import com.mobile.accessorie.model.User;

public interface UserServices {

	User addUser(User user) throws Exception;

	JwtResponse loginUser(User user);

	User UpdateUser(User user);

	User getUserSingle(User user);

	List<User> getallUsers();

	void deleteUser(Integer userId);

}
