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
public class Wish {

	@Id
	private String productName;
	private String productDesc;
	
	private Integer productQuantity;
	
	@Id
	private Long userId;
}
