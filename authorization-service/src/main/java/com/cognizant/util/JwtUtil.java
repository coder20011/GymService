
package com.cognizant.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * Util class for checking and providing utilities
 */
@Service
@Slf4j
public class JwtUtil {

	private String secret = "secret";

	/**
	 * Method to extract user name from token
	 * 
	 * @param token
	 * @return This returns the extracted user name
	 */
	public String extractUsername(String token) {
		log.info("Extracting user name from token");
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Method to extract the expiration time of the token
	 * 
	 * @param token
	 * @return This returns the time of token expiration in milliseconds
	 */
	public Date extractExpiration(String token) {
		log.info("Extracting the expiration time of the token");
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * 
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("Extracting claim");
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	private Claims extractAllClaims(String token) {
		log.info("Extracting all claims");
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Method to check whether token has expired or not
	 * 
	 * @param token
	 * @return This return true if token has expired else returns false
	 */
	private boolean isTokenExpired(String token) {
		log.info("Checking whether token has expired or not");
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Method to generate token
	 * 
	 * @param username
	 * @return This returns the generated token
	 */
	public String generateToken(String username) {
		log.info("Generating token");
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	/**
	 * Method to create the token
	 * 
	 * @param claims
	 * @param subject
	 * @return This returns the generated token
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		log.info("Creating the token");
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	/**
	 * Method to check if a string contains numbers
	 * 
	 * @param strNum
	 * @return This returns true if the string contains numbers else returns false
	 */
	public boolean isNumeric(String strNum) {
		log.info("Checking if a string contains numbers");
		if (strNum == null) {
			return false;
		}
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Method to validate the token
	 * 
	 * @param token
	 * @param userDetails
	 * @return This returns true if the token is valid else returns false
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		log.info("Validating the token");
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
