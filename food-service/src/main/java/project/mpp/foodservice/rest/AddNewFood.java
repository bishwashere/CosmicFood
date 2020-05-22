package project.mpp.foodservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.mpp.foodservice.domain.Food;
import project.mpp.foodservice.persistence.FoodRepository;
import project.mpp.foodservice.service.FoodService;

@RestController
public class AddNewFood {

    @Autowired
    private FoodService service;


    @PostMapping(path="/new")
    public Food addNew(@RequestBody Food food) {
        return service.create(food);
    }
}
