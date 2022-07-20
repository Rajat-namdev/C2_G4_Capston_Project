package com.shopForHome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopForHome.entity.Wish;

@Repository
public interface IWishListRepository extends JpaRepository<Wish, String> {

	
	@Modifying
	@Query(value="delete from Wish w where w.productName=:productName")
	Wish deleteProductFromWishListByName(@Param("productName") String productName);
	
	@Query(value = "select w from Wish w where w.userId=:userId ")
	List<Wish> FindProductInWishListByUserId(@Param("userId") Long userId);
}
