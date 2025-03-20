package kz.solvatech.testtask.mapper;

import kz.solvatech.testtask.dto.IngredientsDto;
import kz.solvatech.testtask.entity.Ingredients;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {
    Ingredients toEntity(IngredientsDto ingredientsDto);
    IngredientsDto toDro(Ingredients ingredients);
}
