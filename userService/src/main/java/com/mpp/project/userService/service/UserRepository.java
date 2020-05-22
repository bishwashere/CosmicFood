package com.mpp.project.userService.service;

import com.mpp.project.userService.Exception.UserNotFoundException;
import com.mpp.project.userService.model.User;
import com.mpp.project.userService.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsernameAndPassword(String un, String psw);
	User findByUsername(String username) throws UserNotFoundException;
	List<User> findByRole(UserRole userRole);

}
