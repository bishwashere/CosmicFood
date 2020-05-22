package com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.Controllers;

import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.Exceptions.OrderNotFoundException;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.Order;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.models.OrderState;
import com.fooddeliveryapp.orderprocessingservice.orderprocessingservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/all")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Order getById(@PathVariable String id) {
        return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order with id "+ id + " not found."));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Order addNewOrder(@RequestBody Order order) {
        order.setOrderState(OrderState.PENDING);
        order.setModifiedBy(order.getCustomerId());
        return orderRepository.save(order);
    }

    //All order of specific customer
    @RequestMapping(path = "/customers/{customerId}", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfCustomer(@PathVariable String customerId) {
        return (orderRepository.findByCustomerId(customerId));
    }

    //All order of specific restaurant
    @RequestMapping(path = "/restaurants/{restaurantId}", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfRestaurant(@PathVariable String restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    //All active orders of a customer
    @RequestMapping(path = "/customers/active/{customerId}", method = RequestMethod.GET)
    public List<Order> getActiveOrdersOfCustomer(@PathVariable String customerId) {
        Iterable<Order> allOrders = getAllOrdersOfCustomer(customerId);
        List<Order> activeOrders = new ArrayList<>();
        for (Order o : allOrders) {
            if (!o.getOrderState().equals(OrderState.DELIVERED) && !o.getOrderState().equals(OrderState.REJECTED)) {
                activeOrders.add(o);
            }
        }
        return activeOrders;
    }

    //All pending orders of restaurant
    @RequestMapping(path = "/restaurants/pending/{restaurantId}")
    public List<Order> getPendingOrdersOfRestaurant(@PathVariable String restaurantId) {
        Iterable<Order> allRestaurantOrders = getAllOrdersOfRestaurant(restaurantId);
        List<Order> orders = new ArrayList<Order>();
        for (Order o : allRestaurantOrders) {
            if (o.getOrderState().equals(OrderState.PENDING)) {
                orders.add(o);
            }
        }
        return orders;
    }

    //All active orders of a customer
    @RequestMapping(path = "/restaurants/active/{restaurantId}", method = RequestMethod.GET)
    public List<Order> getActiveOrdersOfRestaurant(@PathVariable String restaurantId) {
        Iterable<Order> allOrders = getAllOrdersOfRestaurant(restaurantId);
        List<Order> activeOrders = new ArrayList<>();
        for (Order o : allOrders) {
            if (o.getOrderState().equals(OrderState.ACCEPTED)) {
                activeOrders.add(o);
            }
        }
        return activeOrders;
    }

    //Accept order
    @RequestMapping(path = "/restaurants/accept/{orderId}", method = RequestMethod.GET)
    public Order acceptOrder(@PathVariable String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Order with id " + orderId + " not found."));
        order.setModifiedBy(order.getRestaurantId());
        order.setOrderState(OrderState.ACCEPTED);
        return orderRepository.save(order);
    }

    //Reject order
    @RequestMapping(path = "/restaurants/reject/{orderId}", method = RequestMethod.GET)
    public Order rejectOrder(@PathVariable String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found."));
        order.setModifiedBy(order.getRestaurantId());
        order.setOrderState(OrderState.REJECTED);
        return orderRepository.save(order);
    }

    //All orders waiting pickup
    @RequestMapping(path = "/orders/waitingpickup", method =RequestMethod.GET)
    public List<Order> getAllOrdersWaitingPickup(){
        return orderRepository.findByDriverId(null);
    }

    //All orders of a driver
    @RequestMapping(path = "/drivers/{driverId}", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfDriver(@PathVariable String driverId){
        return orderRepository.findByDriverId(driverId);
    }

    //Active orders of driver
    @RequestMapping(path = "/drivers/active/{driverId}", method = RequestMethod.GET)
    public List<Order> getActiveOrdersOfDriver(@PathVariable String driverId) {
        Iterable<Order> allOrders = getAllOrdersOfDriver(driverId);
        List<Order> activeOrders = new ArrayList<>();
        for (Order o : allOrders) {
            if (!o.getOrderState().equals(OrderState.DELIVERED)) {
                activeOrders.add(o);
            }
        }
        return activeOrders;
    }

    //Select pickup
    @RequestMapping(path = "/drivers/selectpickup/{orderId}/{driverId}", method = RequestMethod.GET)
    public Order selectPickup(@PathVariable String orderId, @PathVariable String driverId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order with id "+ orderId + "not found."));
        order.setDriverId(driverId);
        return orderRepository.save(order);
    }

    //pick up order
    @RequestMapping(path = "/pickup/{orderId}")
    public Order pickUp(@PathVariable String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order with id "+ orderId + "not found."));
        order.setOrderState(OrderState.PICKEDUP);
        order.setModifiedBy(order.getDriverId());
        return orderRepository.save(order);
    }

    // deliver order
    @RequestMapping(value = "/deliver/{orderId}", method = RequestMethod.GET)
    public Order deliver(@PathVariable String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order with id "+ orderId + "not found."));
        order.setOrderState(OrderState.DELIVERED);
        order.setModifiedBy(order.getDriverId());
        return orderRepository.save(order);
    }
}


