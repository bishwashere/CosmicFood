package project.mpp.foodservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.mpp.foodservice.domain.Food;

import project.mpp.foodservice.service.FoodService;

@RestController
public class GetFood {

    @Autowired
    private FoodService service;

//    @Cacheable(value = "post-single", key = "#id", unless = "#result.shares < 500")
    @Cacheable(value = "post-single", key = "#id")
    @GetMapping("/foods/{id}")
    public Food getFood(@PathVariable String id) {
        Food foodById = service.findById(id);
        System.out.println("Get food for ID: "+id); // This message will not print twice if Redis is working.
        return new Food(foodById.getId(), foodById.getName(), foodById.getPrice(), foodById.getDescription(), foodById.getImageUrl(), foodById.getRestaurantId());
    }
}
