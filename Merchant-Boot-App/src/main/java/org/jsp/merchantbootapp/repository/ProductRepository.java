package org.jsp.merchantbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.brand=?1")
	public List<Product> findByBrand(String brand);

	@Query("select p from Product p where p.category=?1")
	public List<Product> findByCategory(String category);

	@Query("select p from Product p where p.merchant.id=?1")
	public List<Product> findByMerchantId(int id);

	@Query("select p from Product p where p.merchant.phone=?1 and p.merchant.password=?2")
	public Optional<Product> findByMerchantPhone(long phone, String password);
}
