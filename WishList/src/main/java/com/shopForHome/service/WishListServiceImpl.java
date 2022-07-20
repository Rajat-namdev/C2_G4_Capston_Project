package com.shopForHome.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopForHome.entity.Wish;
import com.shopForHome.repository.IWishListRepository;

@Service
public class WishListServiceImpl implements IWishListService {

	@Autowired
	private IWishListRepository wishListRepository;
	
	@Override
	public Wish addProductsInWishList(Wish wish) {
		
		return wishListRepository.save(wish);
	}

	@Override
	public List<Wish> getWishListOfUser(Long userId) {
		
		List<Wish> list =  wishListRepository.FindProductInWishListByUserId(userId);

			
//		return list.stream().filter(wish->wish.getUserId().equals(userId)).collect(Collectors.toList());
		
		return list;
	}

	@Override
	public Wish deleteProductFromWishList(String name) {
		
		return wishListRepository.deleteProductFromWishListByName(name);
	}

	@Override
	public List<Wish> getAllProductsinWishList() {
		
		return wishListRepository.findAll();
	}

}
