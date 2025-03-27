package kz.solvatech.testtask.exception;

public class IngredientNotFoundException extends IllegalArgumentException {
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
