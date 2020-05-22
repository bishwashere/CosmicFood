package com.mpp.project.userService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpp.project.userService.model.User;
import com.mpp.project.userService.model.UserRole;
import com.mpp.project.userService.service.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(UserController.class)
class UserControllerTest {



        @Autowired
        MockMvc mockMvc;

        @Autowired
        private ObjectMapper mapper;

        @Autowired
        private UserController controller;

        @MockBean
        private UserRepository repository;

        User user1, user2, user3, user4, user2s;


        @BeforeEach
        void setup() throws JsonProcessingException {

            user1=new User("1","pradip","password","CUSTOMER","hari","hari","hari@gmail.com","saasa","asasaas","saass",12,"sasas","askjas");
            user2=new User("2","hari","password","RESTAURANT","hari","hari","hari@gmail.com","saasa","asasaas","saass",12,"sasas","askjas");
            user2s=new User("2","shyam","password","RESTAURANT","shyam","hari","hari@gmail.com","saasa","asasaas","saass",12,"sasas","askjas");
            user3=new User("3","ram","password", "ADMIN","hari","hari","hari@gmail.com","saasa","asasaas","saass",12,"sasas","askjas");
            user4=new User("4","gita","password","ADMIN","hari","hari","hari@gmail.com","saasa","asasaas","saass",12,"sasas","askjas");

        }

        @Test
        public void getAllUsers() {
            Mockito.when(repository.findAll())
                    .thenReturn(Stream.of(user1, user2, user3).collect(Collectors.toList()));

            Assertions.assertThat(3).isEqualTo(controller.getAllUsers().size());
        }

    @Test
    void register() {
        Mockito.when(repository.save(user1)).thenReturn(user1);
        Assertions.assertThat(user1).isEqualTo(controller.register(user1));
    }

    @Test
    void updateUser() {
        Mockito.when(repository.findByUsername(user2.getUsername())).thenReturn(user2);
        Mockito.when(repository.save(user2)).thenReturn(user2);
        Assertions.assertThat("shyam").isEqualTo(controller.updateUser(user2s,user2.getUsername()).getFirstName());

    }
    @Test
    void getUserById() {
        String id = "1";
        Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(user1));

        Assertions.assertThat(user1).isEqualTo(controller.getUserById("1"));
    }

    @Test
    void getUserByUsername() {
        String username = "pradip";
        Mockito.when(repository.findById(username)).thenReturn(java.util.Optional.ofNullable(user1));

        Assertions.assertThat(user1).isEqualTo(controller.getUserById("pradip"));
    }

    @Test
    void getAllRestaurants() {

        Mockito.when(repository.findAll()).thenReturn(Stream.of(user1, user2, user3,user4).collect(Collectors.toList()));
        Assertions.assertThat(1).isEqualTo(controller.getAllRestaurants().size());
    }

//    @Test
//    void getAllDrivers() {
//        Mockito.when(repository.findAll())
//                .thenReturn(Stream.of(user1, user2, user3,user4).collect(Collectors.toList()));
//        Assertions.assertThat(1).isEqualTo(controller.getAllDrivers().size());
//    }

//    @Test
//    void getAllCustomers() {
//        Mockito.when(repository.findAll())
//                .thenReturn(Stream.of(user1, user2, user3,user4).collect(Collectors.toList()));
//        Assertions.assertThat(1).isEqualTo(controller.getAllCustomers().size());
//    }

//    @Test
//    void getAllAdmins() {
//        Mockito.when(repository.findAll())
//                .thenReturn(Stream.of(user1, user2, user3,user4).collect(Collectors.toList()));
//        Assertions.assertThat(1).isEqualTo(controller.getAllAdmins().size());
//    }

    @Test
    void deleteUserById() {
        String id = "1";
        Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(user1));
        Assertions.assertThat(user1).isEqualTo(controller.deleteUserById("1"));
    }



}