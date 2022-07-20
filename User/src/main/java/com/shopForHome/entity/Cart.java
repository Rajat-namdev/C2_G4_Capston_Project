package com.shopForHome.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
