package com.shopForHome.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopForHome.entity.Cart;
import com.shopForHome.repository.ICartListRepository;

@Service
public class CartListServiceImpl implements ICartListService {

	@Autowired
	private ICartListRepository cartListRepository;
	
	@Override
	public Cart addProductInCardList(Cart cart) {
		
		return cartListRepository.save(cart);
	}

	@Override
	public Set<Cart> getAllProductFromCartList() {
		
		return cartListRepository.findCartListProducts();
	}

	@Override
	public Integer removeProductFromCartList(String name, Long userId) {
		
		Set<Cart> list = cartListRepository.findCartListProducts();
		
//		for(Cart c: list) {
//			if(c.getProductName()==name && c.getUserId()==userId) {
				
				return cartListRepository.deleteProductFromCartListByName(name,userId);
				
//			}
//			
//		}
		

		
		
	}

	@Override
	public Set<Cart> getAllCartProductByUserId(Long userId) {
		
		return cartListRepository.findProductsInCartByUserId(userId);
	}

}
