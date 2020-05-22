package com.fooddeliveryapp.storefront.resources;


import com.fooddeliveryapp.storefront.functionalProgramming.FunctionalQueries;
import com.fooddeliveryapp.storefront.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Functional Controller for admin")
@RequestMapping("/admin")
public class AdminResource {

    //region ANUP
    @ApiOperation("Get all restaurant who have more than specific number of foods, and sort them by their ratings from highest to lowest")
    @RequestMapping(value = "/Query1", method= RequestMethod.GET)
    public List<User> getRestaurantsWithFoodCountAndSortByRating(){
        populateData();
        return FunctionalQueries.getRestaurantsWithFoodCountAndSortByRating.apply(restaurants, foods, 4);
    }

    @ApiOperation("Get all restaurant that a user have bought from and sort them according to restaurants that user has spent most to the restaurants that have spent least on.")
    @RequestMapping(value = "/Query2", method=RequestMethod.GET)
    public List<User> getSortedRestaurantByBuyingHistory(){
        populateData();
        return FunctionalQueries.getSortedRestaurantByBuyingHistory.apply(restaurants, orders, "customer1");
    }

    @ApiOperation("Get All foods with price lower than X and served by restaurants with rating higher than or equal to Y then sort the name of the foods by alphabetical orders")
    @RequestMapping(value = "/Query3", method=RequestMethod.GET)
    public List<Food> getAllFoodsBasedOnPriceAndRestaurantRatings(){
        populateData();
        return FunctionalQueries.getAllFoodsBasedOnPriceAndRestaurantRatings.apply(foods, restaurants, 20.0, 4);
    }

    //endregion

    //region BISHWAS
    @ApiOperation("Get All food Ids for a price range from $1 to $10")
    @RequestMapping(value = "/Query4", method= RequestMethod.GET)
    public List<String> getAllFoodsForPriceRange(){
        populateData();
        return  FunctionalQueries.getAllFoodsForPriceRange.apply(foods);
    }

    @ApiOperation("Sort and get all food IDs of a restaurant with the most order to the least order")
    @RequestMapping(value = "/Query5", method = RequestMethod.GET)
    public List<String> getAllTopFoodForRestaurant(){
        populateData();
        return FunctionalQueries.topFoodsForRestaurant.apply(foods, orders);
    }

    @ApiOperation("Get all restaurant IDs whose 'total foods average rating' is among top 10 of all restaurants and that restaurant is also among the 'top 10 ordered' from restaurant")
    @RequestMapping(value = "/Query6", method = RequestMethod.GET)
    public List<String> getAllTopRestaurantsInTown(){
        populateData();
        return FunctionalQueries.topRestaurantsInTown.apply(orders, foods);
    }

    //endregion

    //region SAMSHER

    @ApiOperation("Get all users who has already rejected 10 order after order placed")
    @RequestMapping(value ="/Query7", method = RequestMethod.GET)
    public List<User> getMostCanceledUsers(){
        populateData();
        return FunctionalQueries.getMostCanceledUsers.apply(orders, users, 1);
    }

    @ApiOperation("Get top n drivers with deliver/cancel ratio")
    @RequestMapping(value = "/Query8", method = RequestMethod.GET)
    public List<User> getAllTopDriversWithDeliverCancelRation(){
        populateData();
        return FunctionalQueries.getAllTopDrivers.apply(orders, drivers);
    }

    @ApiOperation("Get all nearby restaurants withgiven radius")
    @RequestMapping(value = "/Query9", method = RequestMethod.GET)
    public List<User> getAllNearestRestaurants(){
        populateData();
        return FunctionalQueries.getAllNearestRestaurants.apply(users,41.0178239,-91.9678598,20d);
    }
    //endregion

    //region PRADIP
    @ApiOperation("Get top ten food items ordered in last week and have rating more than 4")
    @RequestMapping(value = "/Query10", method = RequestMethod.GET)
    public List<Food> getHighlyRatedFoods(){
        populateData();
        return FunctionalQueries.getHighlyRatedFoods.apply(foods, LocalDate.now());
    }

    @ApiOperation("Get all restaurants that have rating less than 2 but have more than x foods")
    @RequestMapping(value = "/Query11", method = RequestMethod.GET)
    public List<User> getLowRatedRestaurants(){
        populateData();
        return FunctionalQueries.getLowRatedRestaurants.apply(restaurants, foods, 5, 5);
    }

    @ApiOperation("Get all drivers who has picked order from x Restaurant and Delivered to y customers")
    @RequestMapping(value = "/Query12", method = RequestMethod.GET)
    public List<User> getAllDriversWithXDeliveriesFromARestaurant(){
        populateData();
        return FunctionalQueries.getAllDriversWithXDeliveriesFromARestaurant.apply(drivers, orders, "restaurant1", 3);
    }

    //endregion

    //region NAVIN
    @ApiOperation("Get all orders with order state not delivered and order state not rejected.")
    @RequestMapping(value = "/Query13", method = RequestMethod.GET)
    public List<Order> getAllActiveOrders(){
        populateData();
        return FunctionalQueries.getAllActiveOrders.apply(orders);
    }

    @ApiOperation("Get all foods that have price below $xyz and from the restaurant that have a rating above 4.")
    @RequestMapping(value ="/Query14", method= RequestMethod.GET)
    public List<Food> getAllFoodsBasedOnPriceAndRestaurantRating(){
        populateData();
        return FunctionalQueries.getAllFoodsBasedOnPriceAndRestaurantRating.apply(foods, restaurants, 10, 4);
    }

    @ApiOperation("Get all users ordered food more than x times and each order is more than $10.")
    @RequestMapping(value = "/Query15", method = RequestMethod.GET)
    public List<User> getAllFoodsBasedOnAreaAndBuyCount(){
        populateData();
        return FunctionalQueries.getAllFoodsBasedOnAreaAndBuyCount.apply(customers, orders, 4, 10);
    }
    //endregion

    //region dummy data
    public static List<User> restaurants, customers, drivers, users;
    public static List<Food> foods;
    public static List<Order> orders;

    public void populateData(){
        restaurants = new ArrayList<>();
        customers = new ArrayList<>();
        drivers = new ArrayList<>();
        foods = new ArrayList<>();
        orders = new ArrayList<>();
        users = new ArrayList<>();

        // Restaurants
        User restaurant1 = new User("restaurant1", "sham.sher", "user1", "Shamsher", "Rana", "example@example.com", "9999999999", "Fairfield", UserRole.RESTAURANT, 5,41.0178239,-91.9678598);//argiro lat long
        User restaurant2 = new User("restaurant2", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 5,41.0178239,-91.9678598);//argiro lat long
        User restaurant3 = new User("restaurant3", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 2,41.0178239,-91.9678598);//argiro lat long
        User restaurant4 = new User("restaurant4", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 1,41.0178239,-91.9678598);//argiro lat long
        User restaurant5 = new User("restaurant5", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 4, 41.0038032,-92.3977244);//ottumwa lat long
        User restaurant6 = new User("restaurant6", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 4);
        User restaurant7 = new User("restaurant7", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 2);
        User restaurant8 = new User("restaurant8", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 1);
        User restaurant9 = new User("restaurant9", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 4);
        User restaurant10 = new User("restaurant10", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 3);
        restaurants = Arrays.asList(restaurant1, restaurant2, restaurant3,restaurant4,restaurant5,restaurant6,restaurant7,restaurant8,restaurant9,restaurant10 );

        //Customers
        User customer1 = new User("customer1", "sham.sher", "customer1", "Shamsher", "Rana", "example@example.com", "9999999999", "Fairfield", UserRole.CUSTOMER, 5);
        User customer2 = new User("customer2", "navin.paudel", "customer2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 4);
        User customer3 = new User("customer3", "navin.paudel", "customer3", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 3);
        User customer4 = new User("customer4", "navin.paudel", "customer4", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 1);
        User customer5 = new User("customer5", "navin.paudel", "customer5", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 1);
        User customer6 = new User("customer6", "navin.paudel", "customer6", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 2);
        User customer7 = new User("customer7", "navin.paudel", "customer7", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 3);
        User customer8 = new User("customer8", "navin.paudel", "customer10", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 2);
        User customer9 = new User("customer9", "navin.paudel", "customer8", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 4);
        User customer10 = new User("customer10", "navin.paudel", "custoer9", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.CUSTOMER, 4);
        customers = Arrays.asList(customer1, customer2, customer3,customer4,customer5,customer6,customer7,customer8,customer9,customer10);

        //Drivers
        User driver1 = new User("driver1", "sham.sher", "user1", "Shamsher", "Rana", "example@example.com", "9999999999", "Fairfield", UserRole.DRIVER, 5);
        User driver2 = new User("driver2", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 3);
        User driver3 = new User("driver3", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 1);
        User driver4 = new User("driver4", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 3);
        User driver5 = new User("driver5", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 4);
        User driver6 = new User("driver6", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 5);
        User driver7 = new User("driver7", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 2);
        User driver8 = new User("driver8", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 1);
        User driver9 = new User("driver9", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.DRIVER, 5);
        User driver10 = new User("driver10", "navin.paudel", "user2", "Navin", "Paudel", "example@example.com", "9999999999", "Des Moines", UserRole.RESTAURANT, 3);
        drivers = Arrays.asList(driver1, driver2, driver3, driver4, driver5, driver6, driver7, driver8, driver8, driver9, driver10);

        // Order Entity
        OrderEntity orderEntity1 = new OrderEntity("food1", 5);

        // Order
        Order order1 = new Order("order1", "customer1", "restaurant1", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 20.0);
        Order order2 = new Order("order2", "customer1", "restaurant1", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 40.0);
        Order order3 = new Order("order3", "customer1", "restaurant1", Arrays.asList(orderEntity1), "driver1", OrderState.PICKEDUP, "driver1", 40.0);
        Order order4 = new Order("order4", "customer1", "restaurant2", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 40.0);
        Order order5 = new Order("order5", "customer1", "restaurant2", Arrays.asList(orderEntity1), "driver1", OrderState.ACCEPTED, "driver1", 40.0);
        Order order6 = new Order("order6", "customer1", "restaurant3", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 40.0);
        Order order7 = new Order("order7", "customer2", "restaurant3", Arrays.asList(orderEntity1), "driver1", OrderState.REJECTED, "driver1", 10.0);
        Order order8 = new Order("order8", "customer3", "restaurant3", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 40.0);
        Order order9 = new Order("order9", "customer3", "restaurant4", Arrays.asList(orderEntity1), "driver1", OrderState.PENDING, "driver1", 40.0);
        Order order10 = new Order("order10", "customer3", "restaurant4", Arrays.asList(orderEntity1), "driver1", OrderState.DELIVERED, "driver1", 40.0);
        orders = Arrays.asList(order1, order2, order3, order4, order5, order6, order7, order8, order9, order10);

        // Food
        Food food1 = new Food("food1", "Apple Pie", 15.5, "Italian Food", "https://images.app.goo.gl/vebgTeEk7Y4Dj2Lg7", "restaurant1", 4, LocalDate.now());
        Food food2 = new Food("food2", "Chicken Sandwich", 25.5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant1", 5, LocalDate.now());
        Food food3 = new Food("food3", "Banana Milk Shake", 15.5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant1", 4, LocalDate.now());
        Food food4 = new Food("food4", "MoMo", 13.5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant1", 5, LocalDate.now());
        Food food5 = new Food("food5", "MoMo", 12.5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant1", 3, LocalDate.now());
        Food food6 = new Food("food6", "MoMo", 5.5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant2", 2, LocalDate.now());
        Food food7 = new Food("food7", "MoMo", 10, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant2", 1, LocalDate.now());
        Food food8 = new Food("food8", "MoMo", 5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant2", 4, LocalDate.now());
        Food food9 = new Food("food9", "MoMo", 5, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant3", 3, LocalDate.now());
        Food food10 = new Food("food10", "MoMo", 10, "Nepali Food", "https://images.app.goo.gl/vxwvS2qsvcKwXueU8", "restaurant3", 4, LocalDate.now());
        foods = Arrays.asList(food1, food2, food3,food4,food5,food6,food7,food8,food9,food10);

        users.addAll(customers);
        users.addAll(restaurants);
        users.addAll(drivers);
    }
    //endregion
}
