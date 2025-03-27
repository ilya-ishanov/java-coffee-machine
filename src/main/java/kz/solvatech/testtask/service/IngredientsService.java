package kz.solvatech.testtask.service;

import kz.solvatech.testtask.entity.IngredientType;
import kz.solvatech.testtask.entity.Ingredients;
import kz.solvatech.testtask.exception.IngredientNotFoundException;
import kz.solvatech.testtask.repository.IngredientsRepository;
import kz.solvatech.testtask.validator.IngredientsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IngredientsService {
    private IngredientsRepository ingredientsRepository;
    private IngredientsValidator ingredientsValidator;

    public String addWater(Long waterMl) {
        ingredientsValidator.validateIngredients(waterMl);

        Ingredients ingredients = ingredientsRepository.findByType(IngredientType.WATER).orElseThrow(() -> new IngredientNotFoundException("Вода не найдена"));
        ingredients.setQuantity(waterMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addCoffee(Long coffeeMl) {
        ingredientsValidator.validateIngredients(coffeeMl);

        Ingredients ingredients = ingredientsRepository.findByType(IngredientType.COFFEE).orElseThrow(() -> new IngredientNotFoundException("Кофе не найден"));
        ingredients.setQuantity(coffeeMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addMilk(Long milkMl) {
        ingredientsValidator.validateIngredients(milkMl);

        Ingredients ingredients = ingredientsRepository.findByType(IngredientType.MILK).orElseThrow(() -> new IngredientNotFoundException("Молоко не найдено"));
        ingredients.setQuantity(milkMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addChocolate(Long chocolateMl) {
        ingredientsValidator.validateIngredients(chocolateMl);

        Ingredients ingredients = ingredientsRepository.findByType(IngredientType.CHOCOLATE).orElseThrow(() -> new IngredientNotFoundException("Шоколад не найден"));
        ingredients.setQuantity(chocolateMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public void addAllIngredients() {
        List<Long> ingredientsIds = List.of(IngredientType.WATER.getId(), IngredientType.COFFEE.getId(),
                IngredientType.MILK.getId(), IngredientType.CHOCOLATE.getId());

        for (Long id : ingredientsIds) {
            Ingredients ingredient = ingredientsRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException("Ингредиент не найден: " + id));
            ingredient.setQuantity(1000L);
            ingredientsRepository.save(ingredient);
        }
    }
}
