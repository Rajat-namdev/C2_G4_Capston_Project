package com.shopForHome.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopForHome.entity.JwtResponse;
import com.shopForHome.entity.User;
import com.shopForHome.helper.JwtUtil;

import reactor.util.annotation.Nullable;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception {

		System.out.println(user);

		try {

			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		

		// fine area

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUserName());

		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT " + token);

		// {"token":"value"}

		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	@GetMapping("/current-user")

	
	public User getCurrentUser(Principal principal) {

		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));

	}
	
}
