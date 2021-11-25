package com.portal.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.app.config.JwtTokenUtil;
import com.portal.app.dto.AuthToken;
import com.portal.app.dto.LoginUserDto;
import com.portal.app.model.User;
import com.portal.app.repository.UserRepository;
import com.portal.app.service.UserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;

	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseEntity<?> register(@RequestBody LoginUserDto loginUser) throws AuthenticationException {
System.out.println("login Controller");
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getUsername(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final String token = jwtTokenUtil.generateToken(authentication);
	        System.out.println("token"+token);
	        return ResponseEntity.ok(new AuthToken(token));
	    }
	 //userDerails
	 
	 @GetMapping("/userDetails")
	 public ResponseEntity getUserDetails(){
		 System.out.println("UserDetails");
		Map<String, String> user= userService.getUserDetails();
		System.out.println(user);
		return ResponseEntity.ok(user);
	 }
	 
}
