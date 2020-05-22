package com.mpp.project.userService.controller;

import com.mpp.project.userService.Exception.UserNotFoundException;
import com.mpp.project.userService.model.User;
import com.mpp.project.userService.model.UserRole;
import com.mpp.project.userService.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		userRepo.save(user);
		return user;
	}

	@PostMapping(value = "/update/{username}")
	public User updateUser(@RequestBody User user, @PathVariable String username){

		User userdb = userRepo.findByUsername(username);
		if(userdb == null) throw new UserNotFoundException("User with username "+ username +"not found.");
		userdb.setFirstName(user.getFirstName());
		userdb.setLastName(user.getLastName());
		userdb.setEmail(user.getEmail());
		userdb.setPhone(user.getPhone());
		final User updatedUser = userRepo.save(userdb);
		return updatedUser;
	}
	
	@GetMapping(path = "/all")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}

	@GetMapping(path = "/{id}")
	public User getUserById(@PathVariable String id)
	{
		return userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User with"+id+" not fount"));
	}

	@GetMapping(path = "/singleuser/{username}")
	public User getUserByUsername(@PathVariable String username){
		Iterable<User> users =  userRepo.findAll();
		for(User u: users){
			if(u.getUsername().equals(username)){
				return u;
			}
		}
		return null;
	}

	@GetMapping(path = "/restaurants")
	public List<User> getAllRestaurants(){
		List<User> restaurants = new ArrayList<>();
		List<User> allUsers = userRepo.findAll();
		for(User u: allUsers){
			String role = u.getRole();
			if(role != null && role.equals(UserRole.RESTAURANT.toString())){
				restaurants.add(u);
			}
		}
		return restaurants;
	}

	@GetMapping(path = "/drivers")
	public List<User> getAllDrivers(){
		List<User> drivers = new ArrayList<>();
		List<User> allUsers = userRepo.findAll();
		for(User u: allUsers){
			String role = u.getRole();
			if(role != null && role.equals(UserRole.DRIVER.toString())){
				drivers.add(u);
			}
		}
		return drivers;
	}

	@GetMapping(path ="/customers")
	public List<User> getAllCustomers(){
		List<User> customers = new ArrayList<>();
		List<User> allUsers = userRepo.findAll();
		for(User u: allUsers){
			String role = u.getRole();
			if(role != null && role.equals(UserRole.CUSTOMER)){
				customers.add(u);
			}
		}
		return customers;
	}

	@GetMapping(path ="/admins")
	public List<User> getAllAdmins(){
		List<User> users =  userRepo.findByRole(UserRole.ADMIN);
		return users;
	}

	@GetMapping(value = "/delete/{id}")
	public User deleteUserById(@PathVariable("id") String id) {
		User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException( "User with id " + id + "not found."));
		userRepo.deleteById(id);
		return user;
	}

	@PostMapping(value = "/usersbyids")
	public Iterable<User> getAllUsersByIds(@RequestBody List<String> ids){
		return userRepo.findAllById(ids);
	}



}
