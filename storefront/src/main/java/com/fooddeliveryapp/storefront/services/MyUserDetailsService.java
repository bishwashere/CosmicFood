package com.fooddeliveryapp.storefront.services;

import com.fooddeliveryapp.storefront.Constants.ServicesUrl;
import com.fooddeliveryapp.storefront.models.User;
import com.fooddeliveryapp.storefront.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = restTemplate.getForObject(ServicesUrl.userServiceUrl + "/singleuser/" + userName, User.class);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
