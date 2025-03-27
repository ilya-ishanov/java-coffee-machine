package kz.solvatech.testtask.validator;

import kz.solvatech.testtask.dto.RecipeDto;
import kz.solvatech.testtask.entity.IngredientType;
import kz.solvatech.testtask.exception.RecipeValidatorException;
import org.springframework.stereotype.Component;

@Component
public class RecipeValidator {
    public void recipeValidator(RecipeDto recipeDto) {
        boolean hasWater = recipeDto.getIngredients().stream()
                .anyMatch(i -> i.getIngredientsId().equals(IngredientType.WATER.getId()));

        boolean hasCoffee = recipeDto.getIngredients().stream()
                .anyMatch(i -> i.getIngredientsId().equals(IngredientType.COFFEE.getId()));

        if (!hasWater || !hasCoffee) {
            throw new RecipeValidatorException("Обязательные ингредиенты: вода и кофе");
        }
    }
}
