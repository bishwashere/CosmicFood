package project.mpp.foodservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.mpp.foodservice.domain.Food;
import project.mpp.foodservice.exceptions.FoodNotFoundException;
import project.mpp.foodservice.persistence.FoodRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class FoodService {
    private FoodRepository foodPersistence;

    public FoodService(FoodRepository foodPersistence){
        this.foodPersistence=foodPersistence;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public Food create(Food food){
        return foodPersistence.save(food);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Food findById(String id){
        return foodPersistence.findById(id).orElseThrow(() ->
            new FoodNotFoundException("Food not found with this id: " + id));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Iterable<Food> findAllById(Iterable<String> ids){
        return foodPersistence.findAllById(ids);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Food> findAll(){
        List<Food> food = new ArrayList<>();
        Iterator<Food> iterator = foodPersistence.findAll().iterator();
        iterator.forEachRemaining(food::add);
        return food;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Food food){
        foodPersistence.delete(food);
    }


}
