package kz.solvatech.testtask.controller;

import kz.solvatech.testtask.dto.OrderDto;
import kz.solvatech.testtask.dto.RecipeDto;
import kz.solvatech.testtask.service.OrderService;
import kz.solvatech.testtask.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class CoffeeMachineController {
    private OrderService orderService;
    private RecipeService recipeService;

    @GetMapping("/statistics/popular")
    public ResponseEntity<String> popularRecipe() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findPopularRecipe());
    }

    @PostMapping("/recipe")
    public ResponseEntity<RecipeDto> addRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeDto savedRecipe = recipeService.addRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.addOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

}
