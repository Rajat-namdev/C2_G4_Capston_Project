package com.shopForHome.service;

import java.util.Set;

import com.shopForHome.entity.Cart;

public interface ICartListService {
	
	public Cart addProductInCardList(Cart cart);
	
	public Set<Cart> getAllProductFromCartList();
	
	public Integer removeProductFromCartList(String name, Long userId);
	
	public Set<Cart> getAllCartProductByUserId(Long userId);

}
