package kz.solvatech.testtask.service;

import kz.solvatech.testtask.dto.OrderDto;
import kz.solvatech.testtask.entity.Ingredients;
import kz.solvatech.testtask.entity.Order;
import kz.solvatech.testtask.entity.Recipe;
import kz.solvatech.testtask.entity.RecipeIngredients;
import kz.solvatech.testtask.exception.WorkingHoursException;
import kz.solvatech.testtask.mapper.OrderMapper;
import kz.solvatech.testtask.repository.IngredientsRepository;
import kz.solvatech.testtask.repository.OrderRepository;
import kz.solvatech.testtask.repository.RecipeIngredientsRepository;
import kz.solvatech.testtask.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private OperationalService operationalService;
    private RecipeRepository recipeRepository;

    private IngredientsRepository ingredientsRepository;
    private RecipeIngredientsRepository recipeIngredientsRepository;

    public String findPopularRecipe() {
        Long recipeId = orderRepository.findPopularRecipe();
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Рецепт с id " + recipeId + " не найден" ));
        return recipe.getName();
    }

    public OrderDto addOrder(OrderDto orderDto) {
        log.info("Calling fetchHolidays method");

        if (!operationalService.isWorkingHours()) {
            throw new WorkingHoursException("Not working hours");
        }
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);

        Long recipeId = savedOrder.getRecipeId();

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Рецепт не найден"));
        log.info("Название Кофе: " + recipe.getName());

        List<RecipeIngredients> recipeIngredients = recipeIngredientsRepository.findByRecipeId(recipe.getId());

        recipeIngredients.forEach(ingredient -> {
            Ingredients ingredients = ingredientsRepository.findById(ingredient.getIngredientsId())
                    .orElseThrow(() -> new IllegalArgumentException("ингредиент не найден"));

            if (ingredients.getQuantity() < ingredient.getAmount()) {
                throw new IllegalArgumentException("Не достаточно ингридиента: " + ingredients.getName()
                        + " " + (ingredient.getAmount() - ingredients.getQuantity()) + " Ml"
                        + " для приготовления " + recipe.getName());
            }

            ingredients.setQuantity(ingredients.getQuantity() - ingredient.getAmount());
            ingredientsRepository.save(ingredients);
        });

        return orderMapper.toDto(savedOrder);
    }
}
