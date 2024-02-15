package org.jsp.productbootapp.dao;

import java.util.List;
import java.util.Optional;
import org.jsp.productbootapp.dto.Product;
import org.jsp.productbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product p) {
		return repository.save(p);
	}

	public Product updateProduct(Product p) {
		return repository.save(p);
	}

	public Optional<Product> findById(int id) {
		return repository.findById(id);
	}

	public boolean delete(int id) {
		Optional<Product> dBProduct = findById(id);
		if (dBProduct.isPresent()) {
			repository.delete(dBProduct.get());
			return true;
		}
		return false;
	}

	public List<Product> findAll() {
		return repository.findAll();
	}
}
