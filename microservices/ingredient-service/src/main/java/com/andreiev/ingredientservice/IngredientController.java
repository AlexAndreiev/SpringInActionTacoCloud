package com.andreiev.ingredientservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/ingredients")
public class IngredientController {

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String id) {
        return new Ingredient("Some code", "Test ingredient");
    }
}
