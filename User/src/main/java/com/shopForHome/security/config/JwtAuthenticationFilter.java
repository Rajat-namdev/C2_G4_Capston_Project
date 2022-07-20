package com.shopForHome.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopForHome.helper.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get Jwt
		//Check jwt start from Bearer
		//validate
		
		String requestTokenHeader = request.getHeader("Authorization");
		String userName=null;
		String jwtToken=null;
		
		//check null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
				userName = this.jwtUtil.extractUsername(jwtToken);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userName);
			
			//security
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}else {
				System.out.println("Token is not validated !");
			}
			
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}
}
