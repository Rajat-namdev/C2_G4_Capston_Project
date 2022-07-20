package com.shopForHome.service;

import java.util.List;
import java.util.Set;

import com.shopForHome.entity.Wish;

public interface IWishListService {
	
	public Wish addProductsInWishList(Wish wish);
	
	public List<Wish> getWishListOfUser(Long userId);
	
	public Wish deleteProductFromWishList(String name);

	public List<Wish> getAllProductsinWishList();
}
