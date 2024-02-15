package org.jsp.productbootapp.repository;

import org.jsp.productbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
