package project.mpp.foodservice.exceptions;

public class FoodNotFoundException extends RuntimeException {
    String message;

    public FoodNotFoundException(String foodId) {
        this.message = "Food with id " + foodId + "not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
