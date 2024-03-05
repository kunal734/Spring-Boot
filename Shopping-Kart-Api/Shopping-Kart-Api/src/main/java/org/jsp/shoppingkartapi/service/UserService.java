package org.jsp.shoppingkartapi.service;

import java.util.List;
import java.util.Optional;

import org.jsp.shoppingkartapi.dao.UserDao;
import org.jsp.shoppingkartapi.dto.ResponseStructure;
import org.jsp.shoppingkartapi.exception.UserNotFoundException;
import org.jsp.shoppingkartapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User Saved");
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> recUser = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = new User();
			dbUser.setId(user.getId());
			dbUser.setName(user.getName());
			dbUser.setGender(user.getGender());
			dbUser.setAge(user.getAge());
			dbUser.setEmail(user.getEmail());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());

			structure.setMessage("User Updated");
			structure.setData(userDao.saveUser(user));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("User Id Not Found");
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User Id Not Found");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Found");
			structure.setData("User Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			userDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("User Not Found");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findAll();
		structure.setData(users);
		if (users.size() > 0) {
			structure.setMessage("User Saved");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("User Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<User>> verifyUserByPhone(long phone, String password) {
		Optional<User> recUser = userDao.verifyUserByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Verified");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Or Password");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUserByEmail(String email, String password) {
		Optional<User> recUser = userDao.verifyUserByEmail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Verified");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Email Or Password");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUserById(int id, String password) {
		Optional<User> recUser = userDao.verifyUserById(id, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Verified");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Id Or Password");
	}
}
