package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizzas")

public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private IngredientService ingredientService;

    // Index
    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll(null);
    }

    // Search
    @GetMapping
    public List<Pizza> search(String keyword) {
        return pizzaService.findAll(keyword);

    }

    // Show by id
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
    }

    // Create
    @PostMapping
    public Pizza store(@RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    // Update
    @PutMapping("/{id}")
    public Pizza update(@RequestBody Pizza pizza, @PathVariable Integer id) {
        pizza.setId(id);
        return pizzaService.update(pizza);
    }

    // Delete By Id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        pizzaService.deleteById(id);
    }

}
