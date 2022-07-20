package com.shopForHome.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopForHome.entity.Wish;
import com.shopForHome.service.IWishListService;

@RestController
@RequestMapping("/WishList")
public class WishController {

	@Autowired
	private IWishListService wishListService;
	
	@PostMapping("/new-wish")
	public Wish addWish(@RequestBody Wish wish) {
		
		return wishListService.addProductsInWishList(wish);
		
	}
	
	@GetMapping("/wish/{userId}")
	public List<Wish> getWishList(@PathVariable("userId") Long userId){
		
		return wishListService.getWishListOfUser(userId);
	}
	
	@GetMapping("/wishs")
	public List<Wish> getAllWishListAlongWithUserId(){
		
		return wishListService.getAllProductsinWishList();
		
	}
}
