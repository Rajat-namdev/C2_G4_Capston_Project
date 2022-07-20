package com.shopForHome.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shopForHome.security.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Lazy
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
		
		authManager.authenticationProvider(authenticationProvider());
		
		
	}
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
						.antMatchers("/Welcome/**").permitAll()
						.antMatchers("/","/User/new-user").permitAll()
						.antMatchers("/","/User/**").hasAnyAuthority("ADMIN","SUPPLIER","USER")
						.antMatchers("/WishList/**").hasAuthority("USER")
						.and()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and().cors().and().csrf().disable();
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
 	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	



}
