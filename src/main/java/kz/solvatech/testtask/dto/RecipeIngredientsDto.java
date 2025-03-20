package kz.solvatech.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientsDto {
    private Long ingredientsId;
    private Long amount;
}
