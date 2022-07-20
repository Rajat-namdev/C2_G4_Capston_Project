package com.shopForHome.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopForHome.entity.Cart;
import com.shopForHome.service.ICartListService;

@RestController
@RequestMapping("/CartList")
public class CartListController {

	
	@Autowired
	private ICartListService cartListService;
	
	@PostMapping("/new-product")
	public Cart addProductInCart(@RequestBody Cart cart) {
		
		return cartListService.addProductInCardList(cart); 
	}
	
	@GetMapping("/carts")
	public Set<Cart> getAllCartList(){
		
		return cartListService.getAllProductFromCartList();
		
	}
	
	@DeleteMapping("/{productName}/{userId}")
	public Integer removeProduct(@PathVariable("productName") String productName,@PathVariable("userId") Long userId) {
	
	return cartListService.removeProductFromCartList(productName, userId);

	}
	
	@GetMapping("/cart/{userId}")
	public Set<Cart> getCartByUserId(@PathVariable("userId") Long userId){
		
		return cartListService.getAllCartProductByUserId(userId);
	}
}

