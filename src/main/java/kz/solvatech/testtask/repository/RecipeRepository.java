package kz.solvatech.testtask.repository;

import kz.solvatech.testtask.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
