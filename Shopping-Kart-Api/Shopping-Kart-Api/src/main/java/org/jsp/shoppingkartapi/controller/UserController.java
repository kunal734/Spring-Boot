package org.jsp.shoppingkartapi.controller;

import java.util.List;

import org.jsp.shoppingkartapi.dto.ResponseStructure;
import org.jsp.shoppingkartapi.model.User;
import org.jsp.shoppingkartapi.service.UserService;
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
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		return userService.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return userService.findAll();
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUserByPhone(@RequestParam long phone,@RequestParam String password) {
		return userService.verifyUserByPhone(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verifyUserByEmail(@RequestParam String email,@RequestParam String password) {
		return userService.verifyUserByEmail(email, password);
	}

	@PostMapping("/verify-by-id")
	public ResponseEntity<ResponseStructure<User>> verifyUserById(@RequestParam int id,@RequestParam String password) {
		return userService.verifyUserById(id, password);
	}
}