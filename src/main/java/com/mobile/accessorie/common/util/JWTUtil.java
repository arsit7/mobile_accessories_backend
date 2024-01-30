package com.mobile.accessorie.common.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.accessorie.security.JwtHelper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
	
	
	static final long TIME_OUT = 30 * 60 * 1000;
	static final String JWT_SECRET = "shudfhsishjdfhhdfhsfhsqefhdfhonvbvkiwefaosduf";

	public static String generateJwtToken(String userName) {

		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + TIME_OUT);

		String token = Jwts.builder().setSubject((userName)).setIssuedAt(new Date()).setExpiration(new Date())
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, JWTUtil.JWT_SECRET).compact();
		return token;
	}
}
