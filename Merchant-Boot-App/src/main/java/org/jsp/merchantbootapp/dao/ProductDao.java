package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product p) {
		return productRepository.save(p);
	}

	public Product updateProduct(Product p) {
		return productRepository.save(p);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public boolean delete(int id) {
		Optional<Product> dBProduct = findById(id);
		if (dBProduct.isPresent()) {
			productRepository.delete(dBProduct.get());
			return true;
		}
		return false;
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> findByMerchantId(int id) {
		return productRepository.findByMerchantId(id);
	}
}
