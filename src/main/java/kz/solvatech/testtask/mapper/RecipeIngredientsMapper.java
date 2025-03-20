package kz.solvatech.testtask.mapper;

import kz.solvatech.testtask.dto.RecipeIngredientsDto;
import kz.solvatech.testtask.entity.RecipeIngredients;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeIngredientsMapper {
    RecipeIngredients toEntity(RecipeIngredientsDto recipeIngredientsDto);
    RecipeIngredientsDto toDto(RecipeIngredients recipeIngredients);
}
