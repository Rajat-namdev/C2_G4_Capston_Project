package com.shopForHome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopForHome.entity.User;
import com.shopForHome.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User addNewUser(User user) {
		
		User storeUserDetails = userRepository.findAllUser(user.getEmail());
		if(storeUserDetails!=null) throw new RuntimeException("User Already Exits");
		
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		
		return userRepository.findById(userId).get();
	}

	@Override
	public List<User> getAllUserList() {
		
		return userRepository.findAll();
	}




}
