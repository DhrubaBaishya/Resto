package com.resto.apiGateway.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.resto.commonModel.exception.AuthorizationNotFoundException;
import com.resto.commonModel.exception.IncorrectAuthorizationException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
	@Value("${app.secret}")
	private String jwtSecret;

	public boolean validateJWTToken(HttpServletRequest request) {
		String token = parseJwt(request);
		logger.info("TOKEN: " + token);
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
			throw new IncorrectAuthorizationException("Invalid JWT signature");
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
			throw new IncorrectAuthorizationException("Invalid JWT token");
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
			throw new IncorrectAuthorizationException("JWT token is expired");
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
			throw new IncorrectAuthorizationException("JWT token is unsupported");
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
			throw new IncorrectAuthorizationException("JWT claims string is empty");
		}
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		else {
			throw new AuthorizationNotFoundException("This is a secured resource and the required Authorization is not found. Please check the documentation on the apis.");
		}
	}
	
}
