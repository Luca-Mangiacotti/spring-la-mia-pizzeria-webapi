package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
