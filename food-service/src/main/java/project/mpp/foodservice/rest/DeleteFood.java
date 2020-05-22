package project.mpp.foodservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.mpp.foodservice.domain.Food;
import project.mpp.foodservice.exceptions.FoodNotFoundException;
import project.mpp.foodservice.persistence.FoodRepository;

@RestController
public class DeleteFood {
    @Autowired
    private FoodRepository foodRepository;

    @GetMapping(path="/delete/{foodId}")
    Food delete(@PathVariable String foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(()-> new FoodNotFoundException(foodId));
        foodRepository.delete(food);
        return food;
    }
}
