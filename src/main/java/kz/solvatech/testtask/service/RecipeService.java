package kz.solvatech.testtask.service;

import kz.solvatech.testtask.dto.RecipeDto;
import kz.solvatech.testtask.dto.RecipeIngredientsDto;
import kz.solvatech.testtask.entity.IngredientType;
import kz.solvatech.testtask.entity.Ingredients;
import kz.solvatech.testtask.entity.Recipe;
import kz.solvatech.testtask.entity.RecipeIngredients;
import kz.solvatech.testtask.exception.IngredientNotFoundException;
import kz.solvatech.testtask.mapper.RecipeMapper;
import kz.solvatech.testtask.repository.IngredientsRepository;
import kz.solvatech.testtask.repository.RecipeIngredientsRepository;
import kz.solvatech.testtask.repository.RecipeRepository;
import kz.solvatech.testtask.validator.RecipeValidator;
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
    private final IngredientsRepository ingredientsRepository;
    private final RecipeValidator recipeValidator;

    public RecipeDto addRecipe(RecipeDto recipeDto) {
        recipeValidator.recipeValidator(recipeDto);

        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe savedRecipe = recipeRepository.save(recipe);

        for (RecipeIngredientsDto ingredientDto : recipeDto.getIngredients()) {
            Ingredients ingredients = ingredientsRepository.findById(ingredientDto.getIngredientsId())
                    .orElseThrow(() -> new IngredientNotFoundException("Ингредиент не найден"));

            RecipeIngredients recipeIngredients = new RecipeIngredients();
            recipeIngredients.setRecipeId(savedRecipe.getId());
            recipeIngredients.setIngredientsId(ingredients.getId());
            recipeIngredients.setAmount(ingredientDto.getAmount());

            recipeIngredientsRepository.save(recipeIngredients);
        }

        return recipeMapper.toDto(savedRecipe);
    }
}
