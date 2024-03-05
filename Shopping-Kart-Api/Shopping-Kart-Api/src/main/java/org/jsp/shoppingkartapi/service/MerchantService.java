package org.jsp.shoppingkartapi.service;

import java.util.List;
import java.util.Optional;

import org.jsp.shoppingkartapi.dao.MerchantDao;
import org.jsp.shoppingkartapi.dto.ResponseStructure;
import org.jsp.shoppingkartapi.exception.MerchantNotFoundException;
import org.jsp.shoppingkartapi.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public ResponseStructure<Merchant> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Saved");
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = new Merchant();
			dbMerchant.setId(merchant.getId());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst(merchant.getGst());
			dbMerchant.setPassword(merchant.getPassword());
			structure.setMessage("Merchant Updated Successfully");
			structure.setData(merchantDao.saveMerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException("Merchant Id Not Found");
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Merchant Id Not Found");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData("Merchant Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			merchantDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Merchant Not Found");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.findAll();
		structure.setData(merchants);
		if (merchants.size() > 0) {
			structure.setMessage("Merchant Saved");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Merchant Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchantByPhone(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verified");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Phone Or Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(String email, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchantByEmail(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verified");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Email Or Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyById(int id, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchantById(id, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verified");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Id Or Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByGst(String gst, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchantByGst(gst, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verified");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Gst Or Password");
	}
}
