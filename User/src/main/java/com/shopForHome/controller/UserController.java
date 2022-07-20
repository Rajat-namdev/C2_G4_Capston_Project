package com.shopForHome.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopForHome.entity.User;
import com.shopForHome.service.IUserService;

import reactor.util.annotation.Nullable;

@RestController
@RequestMapping("/User")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/hello")
	public String welcome() {
		
		return "Hello! SpringBOOT:)";
	}
	
	@PostMapping("/new-user")
	public User addUser(@RequestBody  User user) {
		
		return userService.addNewUser(user);
		
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		
		User user =  this.userService.getUserById(id);
		
		Set wishList = this.restTemplate.getForObject("http://wishlist-service/WishList/wish/" + user.getUserId() , Set.class);
		
		Set cartList = this.restTemplate.getForObject("http://cartlist-service/CartList/cart/" + user.getUserId(), Set.class);
		
		user.setWishList(wishList);
		
		user.setCartList(cartList);
		
		
		return user;
	}
	
	
	@GetMapping("/users")
	public List<User> getAll(){
		
		return userService.getAllUserList();
	}
	
	


}
