package kz.solvatech.testtask.service;

import kz.solvatech.testtask.entity.Ingredients;
import kz.solvatech.testtask.repository.IngredientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IngredientsService {
    private IngredientsRepository ingredientsRepository;

    public String addWater(Long waterMl) {
        addIngredientsValidation(waterMl);

        Ingredients ingredients = ingredientsRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(ingredients.getName());
        ingredients.setQuantity(waterMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addCoffee(Long coffeeMl) {
        addIngredientsValidation(coffeeMl);

        Ingredients ingredients = ingredientsRepository.findById(2L).orElseThrow(RuntimeException::new);
        ingredients.setQuantity(coffeeMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addMilk(Long milkMl) {
        addIngredientsValidation(milkMl);

        Ingredients ingredients = ingredientsRepository.findById(3L).orElseThrow(RuntimeException::new);
        System.out.println(ingredients.getName());
        ingredients.setQuantity(milkMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public String addChocolate(Long chocolateMl) {
        addIngredientsValidation(chocolateMl);

        Ingredients ingredients = ingredientsRepository.findById(4L).orElseThrow(RuntimeException::new);
        System.out.println(ingredients.getName());
        ingredients.setQuantity(chocolateMl + ingredients.getQuantity());
        ingredientsRepository.save(ingredients);

        return "Ингредиент: " + ingredients.getName() + ", Кол-во осталось: " + ingredients.getQuantity();
    }

    public void addAllIngredients() {
        List<Long> ingredientsIds = List.of(1L, 2L, 3L, 4L);

        for (Long id : ingredientsIds) {
            Ingredients ingredient = ingredientsRepository.findById(id).orElseThrow(() -> new RuntimeException("Ингредиент не найден: " + id));
            ingredient.setQuantity(1000L);
            ingredientsRepository.save(ingredient);
        }
    }

    private void addIngredientsValidation(Long number) {
        if (number < 0 || number > 1000) {
            throw new IllegalArgumentException("Ингридиенты должны быть не меньше нуля и не больше 1000 мл");
        }
    }

}
