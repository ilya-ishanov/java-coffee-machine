package kz.solvatech.testtask.service;

import kz.solvatech.testtask.dto.RecipeDto;
import kz.solvatech.testtask.entity.Recipe;
import kz.solvatech.testtask.entity.RecipeIngredients;
import kz.solvatech.testtask.mapper.RecipeMapper;
import kz.solvatech.testtask.repository.RecipeIngredientsRepository;
import kz.solvatech.testtask.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final RecipeIngredientsRepository recipeIngredientsRepository;
    private Long recipeId;

    public RecipeDto addRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe savedRecipe = recipeRepository.save(recipe);
        recipeId = savedRecipe.getId();
        return recipeMapper.toDto(savedRecipe);
    }

    public void addIngredients(Long waterMl, Long coffeeMl, Long milkMl, Long chocolateMl) {
        Map<Long, Long> ingredients = new HashMap<>();

        if (waterMl != null) ingredients.put(1L, waterMl);
        if (coffeeMl != null) ingredients.put(2L, coffeeMl);
        if (milkMl != null) ingredients.put(3L, milkMl);
        if (chocolateMl != null) ingredients.put(4L, chocolateMl);

        if (waterMl == null || coffeeMl == null) {
            throw new IllegalArgumentException("Вода и кофе два обязательных параметра");
        }

        ingredients.forEach((ingredientId, amount) -> {
            recipeIngredientsRepository.save(new RecipeIngredients(recipeId, ingredientId, amount));
        });
    }
}
