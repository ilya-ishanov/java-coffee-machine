package kz.solvatech.testtask.repository;

import kz.solvatech.testtask.entity.RecipeIngredients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientsRepository extends CrudRepository<RecipeIngredients, Long> {
    List<RecipeIngredients> findByRecipeId(Long recipeId);
}
