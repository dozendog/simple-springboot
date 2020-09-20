package com.dozendog.simplespringboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dozendog.simplespringboot.model.RqJwt;
import com.dozendog.simplespringboot.model.RsJwt;
import com.dozendog.simplespringboot.service.UserService;
import com.dozendog.simplespringboot.util.JwtUtils;



@RestController
@CrossOrigin
public class AuthenController {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody RqJwt authenticationRequest) throws Exception {

		logger.info("calling AuthenController.createAuthenticationToken()");
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new RsJwt(token));
	}

	private void authenticate(String username, String password) throws Exception {

		logger.info("calling AuthenController.authenticate():"+username+"="+password);
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
