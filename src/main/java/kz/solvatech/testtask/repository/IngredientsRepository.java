package kz.solvatech.testtask.repository;

import kz.solvatech.testtask.entity.IngredientType;
import kz.solvatech.testtask.entity.Ingredients;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientsRepository extends CrudRepository<Ingredients, Long> {

    default Optional<Ingredients> findByType(IngredientType type) {
        return findById(type.getId());
    }
}
