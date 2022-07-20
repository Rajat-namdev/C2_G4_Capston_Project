package com.shopForHome.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MyKey.class)
public class Cart {
	
	@Id
	private String productName;
	
	private double productAmount;
	
	private String productDesc;
	
	private Long productQuantity;
	
	@Id
	private Long userId;
	
}
