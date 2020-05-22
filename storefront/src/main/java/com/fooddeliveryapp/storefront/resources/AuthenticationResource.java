package com.fooddeliveryapp.storefront.resources;


import com.fooddeliveryapp.storefront.Constants.ServicesUrl;
import com.fooddeliveryapp.storefront.models.AuthenticationRequest;
import com.fooddeliveryapp.storefront.models.AuthenticationResponse;
import com.fooddeliveryapp.storefront.models.User;
import com.fooddeliveryapp.storefront.services.MyUserDetailsService;
import com.fooddeliveryapp.storefront.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Authenticate users while logging in")
public class AuthenticationResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @ApiOperation("Register new user")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@Valid @RequestBody User user){
        return restTemplate.postForObject(ServicesUrl.userServiceUrl + "/register", user, User.class);
    }

    @ApiOperation("Log in user. Returns 403 access denied if the username or password is incorrect.")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(true, jwt, "Successful login"));
    }
}
