package com.shopForHome.service;

import java.util.List;

import com.shopForHome.entity.User;

public interface IUserService{
	
	public User addNewUser(User user);
	
	public User getUserById(Long userId);
	
	public List<User> getAllUserList();
	
	
	


}
