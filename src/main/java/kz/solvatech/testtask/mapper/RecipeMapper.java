package kz.solvatech.testtask.mapper;

import kz.solvatech.testtask.dto.RecipeDto;
import kz.solvatech.testtask.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toEntity(RecipeDto recipeDto);
    RecipeDto toDto(Recipe recipe);
}
