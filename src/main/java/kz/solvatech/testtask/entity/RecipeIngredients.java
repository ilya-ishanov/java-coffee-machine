package kz.solvatech.testtask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipeId;
    private Long ingredientsId;
    private Long amount;

    public RecipeIngredients(Long recipeId, Long ingredientsId, Long amount) {
        this.recipeId = recipeId;
        this.ingredientsId = ingredientsId;
        this.amount = amount;
    }
}
