package project.mpp.foodservice.persistence;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.mpp.foodservice.domain.Food;

public interface FoodRepository extends MongoRepository<Food, String> {

    Iterable<Food> findAllFoodByRestaurantId(String restaurantId);
}