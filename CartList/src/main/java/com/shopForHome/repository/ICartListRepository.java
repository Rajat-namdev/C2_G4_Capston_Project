package com.shopForHome.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopForHome.entity.Cart;

@Repository
public interface ICartListRepository extends JpaRepository<Cart, String> {
	
	@Query(value="select c from Cart c order by productId")
	Page<Cart> findCartListForPaginationAndSortById(Pageable pageable);
	
	@Modifying
	@Query(value="delete from Cart c where c.productName=:productName and c.userId=:userId")
	Integer deleteProductFromCartListByName(@Param("productName") String productName, Long userId);

	@Query(value = "select c from Cart c")
	Set<Cart> findCartListProducts();
	
	@Query(value = "select c from Cart c where c.userId=:userId")
	Set<Cart> findProductsInCartByUserId(@Param("userId") Long userId);

}
