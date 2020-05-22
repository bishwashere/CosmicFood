package com.fooddeliveryapp.storefront.resources;


import com.fooddeliveryapp.storefront.Constants.ServicesUrl;
import com.fooddeliveryapp.storefront.exceptions.UserNotFoundException;
import com.fooddeliveryapp.storefront.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "All data regarding users")
@RequestMapping("/users")

public class UserResource {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("Get all users.")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/JSON")
    public Iterable<User> getAllUsers() {
        return restTemplate.getForObject(ServicesUrl.userServiceUrl + "/all", Iterable.class);
    }

    @ApiOperation("Get user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/JSON")
    public User getUserById(@PathVariable String id) {
        User user = restTemplate.getForObject(ServicesUrl.userServiceUrl + "/" + id, User.class);
        if (user != null) return user;
        else throw new UserNotFoundException(id);
    }

    @ApiOperation("Get user by username")
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = "application/JSON")
    public User getUserByUsername(@PathVariable String username) {
        User user = restTemplate.getForObject(ServicesUrl.userServiceUrl + "/singleuser/" + username, User.class);
        if (user != null) return user;
        else throw new UserNotFoundException(username);
    }

    @ApiOperation("Get all restaurants")
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = "application/JSON")
    public Iterable<User> getAllRestaurants() {
        return restTemplate.getForObject(ServicesUrl.userServiceUrl + "/restaurants", Iterable.class);
    }

    @ApiOperation("Get all drivers")
    @RequestMapping(value = "/drivers", method = RequestMethod.GET, produces = "application/JSON")
    public Iterable<User> getAllDrivers() {
        return restTemplate.getForObject(ServicesUrl.userServiceUrl + "/drivers", Iterable.class);
    }

    @ApiOperation("Get all customers")
    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/JSON")
    public Iterable<User> getAllCustomers() {
        return restTemplate.getForObject(ServicesUrl.userServiceUrl + "/customers", Iterable.class);
    }

    @ApiOperation("Update User information")
    @RequestMapping(value = "/update/{username}",method = RequestMethod.POST)
    public User updateUserInfo(@RequestBody User user, @PathVariable String username){
        return restTemplate.postForObject(ServicesUrl.userServiceUrl +"/update/" + username, user, User.class);
    }

    @ApiOperation("Get users by ids")
    @PostMapping(value = "/usersbyids")
    public List<User> getUsersByIds(@RequestBody List<String> ids){
        return restTemplate.postForObject(ServicesUrl.userServiceUrl + "/usersbyids", ids, List.class);
    }
}
