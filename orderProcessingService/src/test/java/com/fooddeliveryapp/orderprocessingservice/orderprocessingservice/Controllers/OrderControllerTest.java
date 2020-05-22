package com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.Order;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.OrderEntity;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.OrderState;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private OrderController controller;

    @MockBean
    private OrderRepository repository;

    Order order1, order2, order3, order4;

    @BeforeEach
    void setup() throws JsonProcessingException {
        List<OrderEntity> foods = new ArrayList<OrderEntity>();
        foods.add(new OrderEntity("foodid1", 2));
        foods.add(new OrderEntity("foodid3", 3));
        foods.add(new OrderEntity("foodid4", 4));

        order1 = new Order("orderid1", "customerid1", "restaurantid1", foods, "driverid1", OrderState.PENDING, "customerid1");
        order2 = new Order("orderid2", "customerid1", "restaurantid1", foods, "driverid2", OrderState.ACCEPTED, "restaurantid1");
        order3 = new Order("orderid3", "customerid2", "restaurantid2", foods, null, OrderState.REJECTED, "customerid1");
        order4 = new Order("orderid4", "customerid2", "restaurantid2", foods, "driverid1", OrderState.DELIVERED, "customerid1");
    }

    @Test
    public void getAll() {
        Mockito.when(repository.findAll())
                .thenReturn(Stream.of(order1, order2, order2, order4).collect(Collectors.toList()));

        Assertions.assertThat(4).isEqualTo(controller.getAll().size());
    }

    @Test
    void getById() {
        String id = "orderid1";
         Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(order1));

        Assertions.assertThat(order1).isEqualTo(controller.getById("orderid1"));
    }

    @Test
    void addNewOrder() {
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.addNewOrder(order1));
    }

    @Test
    void getAllOrdersOfCustomer() {
        Mockito.when(repository.findByCustomerId("customerid1")).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        Assertions.assertThat(2).isEqualTo(controller.getAllOrdersOfCustomer("customerid1").size());
    }

    @Test
    void getAllOrdersOfRestaurant() {
        Mockito.when(repository.findByRestaurantId("restaurantid1")).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        Assertions.assertThat(2).isEqualTo(controller.getAllOrdersOfRestaurant("restaurantid1").size());
    }

    @Test
    void getActiveOrdersOfCustomer() {
        Mockito.when(repository.findByCustomerId("customerid1")).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        Assertions.assertThat(2).isEqualTo(controller.getActiveOrdersOfCustomer("customerid1").size());
    }

    @Test
    void getPendingOrdersOfRestaurant() {
        Mockito.when(repository.findByRestaurantId("restaurantid1")).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        Assertions.assertThat(1).isEqualTo(controller.getPendingOrdersOfRestaurant("restaurantid1").size());
    }

    @Test
    void getActiveOrdersOfRestaurant() {
        Mockito.when(repository.findByRestaurantId("restaurantid1")).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        Assertions.assertThat(1).isEqualTo(controller.getActiveOrdersOfRestaurant("restaurantid1").size());
    }

    @Test
    void acceptOrder() {
        Mockito.when(repository.findById("orderid1")).thenReturn(java.util.Optional.ofNullable(order1));
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.acceptOrder("orderid1"));
    }

    @Test
    void rejectOrder() {
        Mockito.when(repository.findById("orderid1")).thenReturn(java.util.Optional.ofNullable(order1));
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.rejectOrder("orderid1"));
    }

    @Test
    void getAllOrdersWaitingPickup() {
        Mockito.when(repository.findByDriverId(null)).thenReturn((Stream.of(order3).collect(Collectors.toList())));
        Assertions.assertThat(1).isEqualTo(controller.getAllOrdersWaitingPickup().size());
    }

    @Test
    void getAllOrdersOfDriver() {
        Mockito.when(repository.findByDriverId("driverid1")).thenReturn((Stream.of(order1, order4).collect(Collectors.toList())));
        Assertions.assertThat(2).isEqualTo(controller.getAllOrdersOfDriver("driverid1").size());
    }

    @Test
    void getActiveOrdersOfDriver() {
        Mockito.when(repository.findByDriverId("driverid1")).thenReturn((Stream.of(order1, order4).collect(Collectors.toList())));
        Assertions.assertThat(1).isEqualTo(controller.getActiveOrdersOfDriver("driverid1").size());
    }

    @Test
    void selectPickup() {
        Mockito.when(repository.findById("orderid1")).thenReturn(java.util.Optional.ofNullable(order1));
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.selectPickup("orderid1", "driverid1"));

    }

    @Test
    void pickUp() {
        Mockito.when(repository.findById("orderid1")).thenReturn(java.util.Optional.ofNullable(order1));
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.pickUp("orderid1"));
    }

    @Test
    void deliver() {
        Mockito.when(repository.findById("orderid1")).thenReturn(java.util.Optional.ofNullable(order1));
        Mockito.when(repository.save(order1)).thenReturn(order1);
        Assertions.assertThat(order1).isEqualTo(controller.deliver("orderid1"));

    }
}