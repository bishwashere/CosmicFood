package project.mpp.foodservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import project.mpp.foodservice.domain.Food;
import project.mpp.foodservice.persistence.FoodRepository;
import project.mpp.foodservice.service.FoodService;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class FoodServiceApplication {

    @Autowired
    private FoodService service;

    @Autowired
    private FoodRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(FoodServiceApplication.class, args);
    }

    @PostConstruct
    public void checkIfWorks(){

        repository.deleteAll();

        service.create(new Food(null, "Pizza", 5.00, "San Marzano tomato sauce, fresh mozzarella, grated parmesan and Italian sausage\n" +
                "from Il Mondo Vecchio", "https://images.pexels.com/photos/2619967/pexels-photo-2619967.jpeg?cs=srgb&dl=pizza-2619967.jpg&fm=jpg", "restaurant1"));

        service.create(new Food(null, "Hamburger", 3.50, "2,000 calories a day is used for general nutrition advice, but calorie needs vary. Additional nutrition information available upon request.\n" +
                "Applebee's cannot ensure menu items do not contain ingredients that might cause an allergic reaction or impact other dietary restrictions. In addition, some menu items may contain alcohol. Allergen information is available upon request.", "https://images.pexels.com/photos/1633578/pexels-photo-1633578.jpeg?cs=srgb&dl=close-up-photo-of-a-cheese-burger-1633578.jpg&fm=jpg", "restaurant1"));


        service.create(new Food(null, "Biryani", 5.00, "Slowly cooked rice with freshly ground spices and chicken with special herbs. Served with basmati rice, fresh salad and a can of soda.", "https://images.pexels.com/photos/1624487/pexels-photo-1624487.jpeg?cs=srgb&dl=plate-of-rice-and-cooked-meat-1624487.jpg&fm=jpg", "restaurant2"));

        service.create(new Food(null, "Cinnabon® Mini Rolls", 3.50, "10 mini cinnamon rolls, topped with signature cream cheese frosting, are the perfect way to end pizza night", "https://images.pexels.com/photos/267308/pexels-photo-267308.jpeg?cs=srgb&dl=bakery-baking-bread-chocolate-267308.jpg&fm=jpg", "restaurant2"));
    }
}
