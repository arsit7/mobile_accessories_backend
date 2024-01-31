package com.mobile.accessorie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.accessorie.ExceptionHandling.ApiResopnce;
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

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		User updateUser = this.userServices.UpdateUser(user);

		return new ResponseEntity<>(updateUser,
				(null == updateUser) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK);
	}

	@GetMapping("/singleId")
	public ResponseEntity<User> getsingleUser(@RequestBody User user) {
		User userSingle = this.userServices.getUserSingle(user);

		return new ResponseEntity<>(userSingle,
				(null == userSingle) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK);

	}

	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getallUser() {

		return ResponseEntity.ok(this.userServices.getallUsers());
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResopnce> deleteUser(@PathVariable("userId") Integer uid) {

		this.userServices.deleteUser(uid);

		return new ResponseEntity<ApiResopnce>(new ApiResopnce("user deleted  sucessfully..", true), HttpStatus.OK);

	}
}
