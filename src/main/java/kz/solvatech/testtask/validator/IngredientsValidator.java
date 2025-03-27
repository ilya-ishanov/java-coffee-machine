package kz.solvatech.testtask.validator;

import kz.solvatech.testtask.exception.IngredientsValidatorException;
import org.springframework.stereotype.Component;

@Component
public class IngredientsValidator {
    public void validateIngredients(Long number) {
        if (number < 0 || number > 1000) {
            throw new IngredientsValidatorException("Ингридиенты должны быть не меньше нуля и не больше 1000 мл");
        }
    }
}
