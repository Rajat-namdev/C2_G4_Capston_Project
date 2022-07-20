package com.shopForHome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopForHome.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	@Query(value =  "select u from User u where u.userName=:userName")
	public User findUserByName(String userName);

	@Query(value = "select u from User u")
	public List<User> findAllUserList();
	
	@Query(value = "select u from User u where u.email=:email")
	User findAllUser(String email);

}
