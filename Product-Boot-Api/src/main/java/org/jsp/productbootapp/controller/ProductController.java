package org.jsp.productbootapp.controller;

import java.util.List;

import org.jsp.productbootapp.dto.Product;
import org.jsp.productbootapp.dto.ResponseStructure;
import org.jsp.productbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product p) {
		return service.saveProduct(p);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product p) {
		return service.updateProduct(p);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int id) {
		return service.findProductById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		return service.findAllProducts();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
}
