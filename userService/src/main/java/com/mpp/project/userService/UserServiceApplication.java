package com.mpp.project.userService;

import com.mpp.project.userService.model.User;
import com.mpp.project.userService.model.UserRole;
import com.mpp.project.userService.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UserServiceApplication {

	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@PostConstruct
	public void constructDb(){

//		userRepo.deleteAll();
//
//		userRepo.save(new User(null, "user1", "user1" , UserRole.CUSTOMER.toString(), "User1", "User1", "user1@gmail.com", "1000N Fourth Street", "Fairfield", "Iowa", 52557, "32341", ""));
//
//		userRepo.save(new User(null, "user2", "user2" , UserRole.CUSTOMER.toString(), "User2", "User2", "user2@gmail.com", "1000N Fourth Street", "Fairfield", "Iowa", 52557, "32342", ""));
//
//		userRepo.save(new User("restaurant1", "restaurant1", "restaurant1" , UserRole.RESTAURANT.toString(), "restaurant1", "restaurant1", "restaurant1@gmail.com", "1000N Fourth Street", "Fairfield", "Iowa", 52557, "323411", ""));
//
//		userRepo.save(new User("restaurant2", "restaurant2", "restaurant2" , UserRole.RESTAURANT.toString(), "restaurant2", "restaurant2", "restaurant2@gmail.com", "1000N Fourth Street", "Fairfield", "Iowa", 52557, "323422", ""));
//
//		userRepo.save(new User(null, "driver1", "driver1" , UserRole.DRIVER.toString(), "driver1", "driver1", "driver1@gmail.com", "1000N Fourth Street", "Fairfield", "Iowa", 52557, "32342211", ""));


	}



}
