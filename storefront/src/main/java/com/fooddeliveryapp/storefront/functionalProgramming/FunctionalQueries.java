package com.fooddeliveryapp.storefront.functionalProgramming;

import com.fooddeliveryapp.storefront.models.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class FunctionalQueries {

    public static BiFunction<List<User>, String, Optional<User>> getRestaurantById = (restaurants, id) -> restaurants.stream()
            .filter(r -> r.getId().equals(id)).findFirst();


    //region ANUP'S QUERIES
    //QUERY 1
    //get all restaurant who have more than specific number of foods, and sort them by their ratings from highest to lowest
    public static TriFunction<List<User>, List<Food>, Integer, List<User>> getRestaurantsWithFoodCountAndSortByRating =
            (restaurants, foods, foodCount) -> foods.stream()
                    .collect(groupingBy(Food::getRestaurantId))
                    .entrySet()
                    .stream()
                    .filter(group -> group.getValue()
                            .stream().count() > foodCount)
                    .map(id -> getRestaurantById.apply(restaurants, id.getKey()).get())
                    .sorted(Comparator.comparingInt(User::getRating)
                            .reversed())
                    .collect(toList());

    //QUERY 2
    // Get all restaurant that a user have bought from and sort them according to restaurants that user has spent most to the restaurants that have spent least on.
    public static TriFunction<List<User>, List<Order>, String, List<User>> getSortedRestaurantByBuyingHistory =
            (restaurants, orders, customerId) -> orders.stream()
            .filter(order -> order.getCustomerId() == customerId)
                    .collect(groupingBy(Order::getRestaurantId))
                    .entrySet()
                    .stream()
                    .sorted((res1, res2) -> {
                        Double res1Price = res1.getValue().stream().mapToDouble(order -> order.getTotalPrice()).sum();
                        Double res2Price = res2.getValue().stream().mapToDouble(order -> order.getTotalPrice()).sum();
                        return (int) (res2Price - res1Price);
                    })
                    .map(group -> getRestaurantById.apply(restaurants, group.getKey()).get())
            .collect(toList());

    //QUERY 3
    //Get All foods with price lower than X and served by restaurants with rating higher than or equal to Y then sort the name of the foods by alphabetical orders
    public static TetraFunction<List<Food>, List<User>, Double, Integer, List<Food>> getAllFoodsBasedOnPriceAndRestaurantRatings =
            (foods, restaurants, priceRange, rating) -> foods.stream()
            .filter(food-> food.getPrice() <= priceRange)
                    .collect(groupingBy(Food::getRestaurantId))
                    .entrySet()
                    .stream()
            .filter(rid -> getRestaurantById.apply(restaurants, rid.getKey()).get()
                    .getRating() >= rating)
                    .flatMap(key -> key.getValue().stream())
                    .sorted(Comparator.comparing(Food::getName))
            .collect(toList());
    //endregion

    //region BISWAS QUERIES

    //QUERY 4
    //Get All foods ids for a price range 1 -10$
    public static Function<List<Food>, List<String>> getAllFoodsForPriceRange =
            (foodList) ->
                    foodList
                            .stream()
                            .filter(fixture -> fixture.getPrice() > 1 && fixture.getPrice() < 10)
                            .map(Food::getId)
                            .collect(Collectors.toList());

    public static BiFunction<List<Food>, String, String> getFoodByRestaurantId = (foodList, restId) -> foodList
            .stream()
            .filter(r -> r.getRestaurantId().equals(restId)).map(Food::getId).collect(Collectors.toList()).toString();


    //QUERY 5
    //Get All top foods for restaurants
    public static BiFunction<List<Food>, List<Order>, List<String>> topFoodsForRestaurant = (foodList, orders) -> orders
            .stream()
            .collect(Collectors.groupingBy(Order::getRestaurantId, LinkedHashMap::new, counting()))
            .keySet().stream()
            .map(aLong -> getFoodByRestaurantId.apply(foodList, aLong))
            .collect(Collectors.toList());


    //QUERY 6
    //Get all top restaurants in town
    public static BiFunction<List<Order>, List<Food>, List<String>> topRestaurantsInTown =
            (orderList, foodList) -> foodList
                    .stream()
                    .collect(
                            Collectors.groupingBy(Food::getRestaurantId, Collectors.averagingInt(Food::getRating)))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .map(Map.Entry::getKey)
                    .limit(10)
                    // returns stream of restaurant ID
                    .filter(
                            // Step 2: From Order -> Group restaurant ID + order by group count.
                            orderList
                                    .stream()
                                    .filter(order -> order.getOrderState() == OrderState.DELIVERED)
                                    .collect(Collectors.groupingBy(Order::getRestaurantId, LinkedHashMap::new, counting()))
                                    .entrySet()
                                    .stream()
                                    .limit(2)
                                    .map(Map.Entry::getKey)
                                    .collect(Collectors.toList())
                                    // returns list of restaurant ID
                                    ::contains
                    )
                    .collect(Collectors.toList());
    //endregion

    //region SAMSHER's QUERIES

    //Query 7
    //get all users who has already rejected 10 order after order placed
    public static TriFunction<List<Order>,List<User>,Integer,  List<User>> getMostCanceledUsers = (orders,users, rejectedCount) -> orders
            .stream()
            .collect(Collectors.groupingBy(Order::getCustomerId))
            .entrySet()
            .stream()
            .filter((group)->group.getValue()
                    .stream()
                    .collect(Collectors.collectingAndThen(Collectors.toList(),(list)->{//reversing the list
                        Collections.reverse(list);
                        return list.stream();
                    }))
                    .limit(rejectedCount)
                    .filter((order)->order.getOrderState() == OrderState.REJECTED)
                    .count()>=rejectedCount)
            .map((group)->users
                    .stream()
                    .filter((user)->user.getId() == group.getKey())
                    .findFirst().get())
            .collect(Collectors.toList());

    //QUERY 8
    //get Top n drivers with deliver/cancel ratio
    public static BiFunction<List<Order>,List<User>, List<User>> getAllTopDrivers = (orders,drivers) -> orders
            .stream()
            .filter(order -> order.getDriverId()!= null)//initially driver is not set just order has been placed
            .collect(Collectors.groupingBy(Order::getDriverId))
            .entrySet()
            .stream()
            .sorted(Comparator.comparing((entrySet)->{//basis of delivered to rejected ratio
                int rejected = (int) entrySet.getValue().stream().filter(order -> order.getOrderState() == OrderState.REJECTED).count();
                int delivered = (int) entrySet.getValue().stream().filter(order -> order.getOrderState() == OrderState.DELIVERED).count();
                return (rejected == 0 && delivered == 0)?0:(delivered/(delivered+rejected));
            }))
            .map((group)->drivers
                    .stream()
                    .filter((user)->user.getId() == group.getKey())
                    .findFirst().get())
            .collect(Collectors.toList());


    static TetraFunction<Double,Double,Double,Double,Double> distanceFormula = (lat1,lon1,lat2,lon2)->{
        Double dLat = Math.toRadians(lat2-lat1);  // deg2rad below
        Double dLon = Math.toRadians(lon2-lon1);
        Double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        return 6371d * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); // Distance in km
    };

    //QUERY 9
    //get near by restaurants with given radius
    public static TetraFunction<List<User>,Double,Double,Double, List<User>> getAllNearestRestaurants = (restaurants,lat,lng,radius) -> restaurants
            .stream()
            .filter((user)->user.getRole() == UserRole.RESTAURANT)
            .filter((res)->{
                return  distanceFormula.apply(lat,lng,res.getLat(),res.getLng())<=radius;
            })
            .collect(Collectors.toList());

    //endregion

    //region PRADIP'S QUERIES

    //QUERY 10
    //Get top ten food items ordered in last week and have rating more than 4
    public static BiFunction<List<Food>, LocalDate, List<Food>> getHighlyRatedFoods = (food, date) -> food.stream()
            .filter(f -> f.getRating() >= 4 && date.getYear() == LocalDate.now().getYear() && date.getMonth() == LocalDate.now().getMonth())
            .limit(2)
            .collect(Collectors.toList());

    //QUERY 11
    // Get all restaurants that have rating less than 2 but have more than x foods
    public static TetraFunction<List<User>,List<Food>,Integer,Integer, List<User>> getLowRatedRestaurants = (restaurants, foods, min_rate, no_foods)->foods
            .stream()
            .collect(Collectors.groupingBy(Food::getRestaurantId))
            .entrySet()
            .stream()
            .filter((restaurantFoods)->restaurantFoods.getValue().size()>=no_foods)
            .map((restaurantFoods)->restaurants.stream().filter(restaurant->restaurant.getId() == restaurantFoods.getKey()).findFirst().get())
            .filter((res)->res.getRating()<=min_rate)
            .collect(Collectors.toList());

    //QUERY 12
    //Get all drivers who has picked order from x Restaurant and Delivered to y customers
    public static TetraFunction<List<User>,List<Order>,String,Integer,List<User>> getAllDriversWithXDeliveriesFromARestaurant=(drivers,orders,restaurantId,no_of_deliveries)->drivers.stream()
            .filter(d->d.getRole()==UserRole.DRIVER)
            .filter(d->orders.stream().filter(o->o.getDriverId() == d.getId() && o.getRestaurantId() == restaurantId && o.getOrderState() == OrderState.DELIVERED).count()>=no_of_deliveries)
            .collect(Collectors.toList());

    //endregion

    //region NAVIN'S QUERIES

    //  QUERY 13
    //  Get all orders with order state not delivered and order state not rejected.
    public static Function<List<Order>, List<Order>> getAllActiveOrders =
            (orders) ->  orders.stream()
                    .filter(o -> o.getOrderState()!= OrderState.DELIVERED && o.getOrderState()!=OrderState.REJECTED)
                    .collect(Collectors.toList());

    //  QUERY 14
    //  Get all foods that have price below $xyz and from the restaurant that have a rating above 4.
    public static TetraFunction<List<Food>, List<User>, Integer, Integer, List<Food>> getAllFoodsBasedOnPriceAndRestaurantRating =
            (foods, restaurants, price, rating) -> foods.stream()
                    .filter(f -> f.getPrice()<=price && restaurants.stream()
                            .filter(res->res.getId() == f.getRestaurantId()).findFirst().get().getRating()>=rating)
                    .collect(Collectors.toList());


    //  QUERY 15
    //  Get all users ordered food more than x times and each order is more than $10.
    public  static TetraFunction<List<User>, List<Order>, Integer, Integer, List<User>> getAllFoodsBasedOnAreaAndBuyCount =
            (customers, allOrders, count, price) -> customers.stream()
                    .filter(customer -> allOrders.stream().filter(
                            order -> order.getOrderState()==OrderState.DELIVERED).count()>count
                            && allOrders.stream().filter(
                            order -> order.getOrderState()==OrderState.DELIVERED)
                            .mapToDouble((order)->order.getTotalPrice()).sum()>price)
                    .collect(Collectors.toList());
    //endregion
}
