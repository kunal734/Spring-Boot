package org.jsp.productbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.productbootapp.dao.ProductDao;
import org.jsp.productbootapp.dto.Product;
import org.jsp.productbootapp.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setData(pDao.saveProduct(p));
		structure.setMessage("Product saved succesfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product p) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setData(pDao.updateProduct(p));
		structure.setMessage("Product Updated succesfully");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Product>> findProductById(int id) {
		Optional<Product> dBProduct = pDao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (dBProduct.isPresent()) {
			structure.setData(dBProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Product Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(pDao.findAll());
		structure.setMessage("List of All Products");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> dbProduct = pDao.findById(id);
		if (dbProduct.isPresent()) {
			pDao.delete(id);
			structure.setMessage("Product Deleted");
			structure.setData("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Product Not Deleted");
		structure.setData("Product Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
