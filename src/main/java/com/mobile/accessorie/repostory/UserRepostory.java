package com.mobile.accessorie.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobile.accessorie.model.User;

@Repository
public interface UserRepostory extends JpaRepository<User, Integer> {
	
	
	/* code */

//	@Query("SELECT u.salt FROM User u WHERE u.email = :email")
//	Optional<String> getSalt(@Param("email") String email);

	Optional<User> findByEmailAndPassword(String email, String password);

	User findByEmail(String email);
}
