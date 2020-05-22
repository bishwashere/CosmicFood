package project.mpp.foodservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.mpp.foodservice.domain.Food;
import project.mpp.foodservice.persistence.FoodRepository;
import project.mpp.foodservice.service.FoodService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GetFoodsByIds {
    @Autowired
    private FoodService service;

    @PostMapping("/getfoodbyids")
    public Iterable<Food> getFood(@RequestBody String[] ids) {
        return service.findAllById(Arrays.asList(ids));
    }
}
