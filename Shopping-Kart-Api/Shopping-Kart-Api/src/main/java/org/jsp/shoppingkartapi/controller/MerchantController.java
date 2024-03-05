package org.jsp.shoppingkartapi.controller;

import java.util.List;

import org.jsp.shoppingkartapi.dto.ResponseStructure;
import org.jsp.shoppingkartapi.model.Merchant;
import org.jsp.shoppingkartapi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name = "id") int id) {
		return merchantService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return merchantService.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		return merchantService.findAll();
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return merchantService.verifyMerchant(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return merchantService.verifyByEmail(email, password);
	}

	@PostMapping("/verify-by-id")
	public ResponseEntity<ResponseStructure<Merchant>> verifyById(@RequestParam int id, @RequestParam String password) {
		return merchantService.verifyById(id, password);
	}

	@PostMapping("/verify-by-gst")
	public ResponseEntity<ResponseStructure<Merchant>> verifyByGst(@RequestParam String gst,
			@RequestParam String password) {
		return merchantService.verifyByGst(gst, password);
	}
}
