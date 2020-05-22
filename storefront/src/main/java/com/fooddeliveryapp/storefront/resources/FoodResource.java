package com.fooddeliveryapp.storefront.resources;
import com.fooddeliveryapp.storefront.models.Food;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static com.fooddeliveryapp.storefront.Constants.ServicesUrl.foodServiceUrl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "All data regarding foods")
@RequestMapping("/foods")
public class FoodResource {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("Get all foods.")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Food> getAllFoods(){
       return restTemplate.getForObject(foodServiceUrl + "/all", Iterable.class);
    }

    @ApiOperation("Get food by id.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Food getFoodById(@PathVariable String id) {
        return restTemplate.getForObject(foodServiceUrl + "/foods/" + id, Food.class);
    }

    @ApiOperation("Get all foods of one restaurant with restaurant ID")
    @RequestMapping(value = "/restaurant/{restaurantId}", method = RequestMethod.GET)
    public Iterable<Food> getAllFoodsOfRestaurant(@PathVariable String restaurantId){
       return restTemplate.getForObject(foodServiceUrl + "/restaurant/" + restaurantId, Iterable.class);
    }

    @ApiOperation("Add new food")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Food addNew(@RequestBody Food food){
        return restTemplate.postForObject(foodServiceUrl + "/new", food, Food.class);
    }

    @ApiOperation("Delete food.")
    @RequestMapping(value = "/delete/{foodId}", method = RequestMethod.POST)
    public Food deleteFood(@PathVariable String foodId){
        return restTemplate.getForObject(foodServiceUrl + "/delete/" + foodId, Food.class);
    }

    @ApiOperation("Find foods by ids.")
    @RequestMapping(value = "/foodsByIds", method  = RequestMethod.POST)
    public Iterable<Food> getFoodsByIds(@RequestBody List<String> ids){
        if(ids == null) throw new NullPointerException();
        return restTemplate.postForObject(foodServiceUrl + "/getfoodbyids", ids, Iterable.class);
    }
}
