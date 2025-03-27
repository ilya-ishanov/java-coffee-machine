package kz.solvatech.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IngredientType {
    WATER(1L),
    COFFEE(2L),
    MILK(3L),
    CHOCOLATE(4L);

    private final Long id;
}
