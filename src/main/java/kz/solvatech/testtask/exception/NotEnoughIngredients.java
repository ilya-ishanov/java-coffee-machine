package kz.solvatech.testtask.exception;

public class NotEnoughIngredients extends IllegalArgumentException {
    public NotEnoughIngredients(String message) {
        super(message);
    }
}
