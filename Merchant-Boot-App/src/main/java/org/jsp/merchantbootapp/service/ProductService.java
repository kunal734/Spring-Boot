package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> dBMerchant = merchantDao.findById(merchant_id);
		if (dBMerchant.isPresent()) {
			Merchant merchant = dBMerchant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			merchantDao.saveMerchant(merchant);
			structure.setData(pDao.saveProduct(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException("Could Not Add Product as Merchant is not Present");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product p) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = pDao.findById(p.getId());
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setBrand(p.getBrand());
			dbProduct.setCategory(p.getCategory());
			dbProduct.setDescription(p.getDescription());
			dbProduct.setCost(p.getCost());
			dbProduct.setImg_url(p.getImg_url());
			dbProduct.setName(p.getName());
			structure.setData(pDao.saveProduct(dbProduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Invalid Id");
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
		throw new ProductNotFoundException("Invalid Id");
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

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByBrand(brand);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Brand");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByCategory(category);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Catgeory");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = pDao.findByMerchantId(id);
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantPhone(long phone, String password) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		Optional<Merchant> merchant = merchantDao.verifyMerchantByPhone(phone, password);
		if (merchant.isPresent()) {
			structure.setData(merchant.get().getProducts());
			structure.setMessage("Products Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Merchant Id");
	}
}
