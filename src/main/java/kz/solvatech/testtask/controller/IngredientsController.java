package kz.solvatech.testtask.controller;

import kz.solvatech.testtask.service.IngredientsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IngredientsController {
    private IngredientsService ingredientsService;

    @PostMapping("/addWater")
    public String addWater(@RequestParam Long waterMl) {
        return ingredientsService.addWater(waterMl);
    }

    @PostMapping("/addCoffee")
    public String adCoffee(@RequestParam Long coffeeMl) {
        return ingredientsService.addCoffee(coffeeMl);
    }

    @PostMapping("/addMilk")
    public String addMilk(@RequestParam Long milkMl) {
        return ingredientsService.addMilk(milkMl);
    }

    @PostMapping("/addChocolate")
    public String addChocolate(@RequestParam Long chocolateMl) {
        return ingredientsService.addChocolate(chocolateMl);
    }

    @PostMapping("/addAll")
    public void addAllIngredients() {
        ingredientsService.addAllIngredients();
    }

}
