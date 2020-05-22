package com.fooddeliveryapp.storefront.resources;

import com.fooddeliveryapp.storefront.Constants.ServicesUrl;
import com.fooddeliveryapp.storefront.models.Order;
import com.fooddeliveryapp.storefront.models.OrderEntity;
import com.fooddeliveryapp.storefront.models.OrderState;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "All data regarding orders")
@RequestMapping("/orders")
public class OrderResource {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/all")
    public Iterable<Order> getAll(){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/all", Iterable.class);
    }

    @GetMapping(path = "/{id}")
    public Order getById(@PathVariable String id){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + id, Order.class);
    }

    @ApiOperation(value = "Place an order by a customer.", consumes = "application/JSON")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Order addNewOrder(@Valid @RequestBody Order order){
        order.setModifiedBy(order.getCustomerId());
        return restTemplate.postForObject(ServicesUrl.orderServiceUrl + "/new", order, Order.class);
    }

    //Get All Orders of a customer
    @ApiOperation("Get all orders of a specific customer")
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, produces = "application/JSON")
    private Iterable<Order> getAllOrdersOfCustomer(@PathVariable String customerId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/customers/" + customerId, Iterable.class);
    }

    //Get All Orders of a restaurant
    @ApiOperation("Get all orders of a specific restaurant")
    @RequestMapping(value = "/restaurants/{restaurantId}", method = RequestMethod.GET, produces = "application/JSON")
    private Iterable<Order> getAllOrdersOfRestaurant(@PathVariable String restaurantId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl +"/restaurants/" + restaurantId, Iterable.class);
    }

    //Get active orders of a customer
    @ApiOperation("Get active order of a specific customer. Active order of customer means the orders of the customer which are not delivered or rejected. Returns an empty list if there is no active orders, otherwise returns list of orders.")
    @RequestMapping(value = "customers/active/{customerId}", method = RequestMethod.GET, produces = "application/JSON")
    private Iterable<Order> getActiveOrdersOfCustomer(@PathVariable String customerId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/customers/active/" + customerId, Iterable.class);
    }

    @ApiOperation("Get all pending orders of a specific restaurant")
    @RequestMapping(value = "/restaurants/pending/{restaurantId}", method = RequestMethod.GET, produces = "application/json")
    private Iterable<Order> getPendingOrdersOfRestaurant(@PathVariable String restaurantId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl +"/restaurants/pending/" + restaurantId, Iterable.class);
    }

    @ApiOperation("Get active order of a specific restaurant. Active oder of a restaurant means all the orders of the restaurant that are accepted by restaurant but not picked up by diver.")
    @RequestMapping(value = "/restaurants/active/{restaurantId}", method = RequestMethod.GET, produces = "application/JSON")
    public Iterable<Order> geActiveOrdersOfRestaurant(@PathVariable String restaurantId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl +"/restaurants/active/" + restaurantId, Iterable.class);
    }

    @ApiOperation(value = "Restaurant accepts the order")
    @RequestMapping(value = "/restaurants/accept/{orderId}", method = RequestMethod.GET)
    public Order acceptOrder(@PathVariable String orderId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/restaurants/accept/" + orderId, Order.class);
    }

    @ApiOperation(value = "Restaurant rejects the order. Returns an order with rejected status.")
    @RequestMapping(value = "/restaurants/reject/{orderId}", method = RequestMethod.GET)
    public Order rejectOrderByRestaurant(@PathVariable String orderId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/restaurants/reject/" + orderId, Order.class);
    }

    @ApiOperation(value ="Get all orders that don't have any drivers yet. Drivers can view these orders and select one order to pickup.")
    @RequestMapping(value = "/orders/waitingpickup",method=RequestMethod.GET)
    public Iterable<Order> getAllOrdersWaitingPickup(){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/orders/waitingpickup", Iterable.class);
    }

    @ApiOperation("Get all orders of a specific driver")
    @RequestMapping(value = "/drivers/{driverId}", method = RequestMethod.GET, produces = "application/JSON")
    private Iterable<Order> getAllOrdersOfDriver(@PathVariable String driverId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/drivers/{driverId}", Iterable.class);
    }

    @ApiOperation("Get active orders of a driver. Active order of drivers means the orders which are selected by driver to pickup, but hasn't delivered yet.")
    @RequestMapping(value = "/drivers/active/{driverId}", method = RequestMethod.GET, produces = "application/JSON")
    private Iterable<Order> getActiveOrderOfDriver(@PathVariable String driverId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/drivers/active/" + driverId, Iterable.class);
    }

    @ApiOperation(value = "Driver selects to pick up order.")
    @RequestMapping(value = "/selectpickup/{orderId}/{driverId}", method = RequestMethod.GET)
    public Order selectPickup(@PathVariable String orderId, @PathVariable String driverId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/drivers/selectpickup/"+orderId + "/" + driverId, Order.class);
    }

    @ApiOperation(value = "Driver picks up the order from restaurant. Order State is changed to accepted.")
    @RequestMapping(value = "/pickup/{orderId}", method = RequestMethod.GET)
    public Order pickUp(@PathVariable String orderId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl +"/pickup/" + orderId, Order.class);
    }

    @ApiOperation(value = "Driver delivers the order to customer.")
    @RequestMapping(value = "/deliver/{orderId}", method = RequestMethod.GET)
    public Order deliver(@PathVariable String orderId){
        return restTemplate.getForObject(ServicesUrl.orderServiceUrl + "/deliver/" + orderId, Order.class);
    }
}
