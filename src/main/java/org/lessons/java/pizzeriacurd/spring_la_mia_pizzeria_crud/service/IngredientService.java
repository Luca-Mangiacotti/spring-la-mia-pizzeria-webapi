package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    // Index

    public List<Ingredient> findAll() {

        return ingredientRepository.findAll();

    }

    // Show by id

    public Ingredient getById(Integer id) {

        return ingredientRepository.findById(id).get();
    }

    // Create
    public Ingredient create(Ingredient ingredient) {

        return ingredientRepository.save(ingredient);
    }

    // Update

    public Ingredient update(Ingredient ingredient) {

        return ingredientRepository.save(ingredient);

    }

    // Delete by id

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);
    }

    // Delete

    public void delete(Ingredient ingredient) {

        ingredientRepository.deleteById(ingredient.getId());
    }
}
