package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    // la repository verrà delegata al servizio e non più al controller
    @Autowired
    private PizzaRepository pizzaRepository;

    // Index con Search
    public List<Pizza> findAll(String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            return pizzaRepository.findByNameContainingIgnoreCase(keyword);
        }

        return pizzaRepository.findAll();
    }

    // Show tramite Id
    public Pizza getById(Integer id) {
        return pizzaRepository.findById(id).get();
    }

    // Find by id
    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    // Exist by id
    public Boolean existById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    // Creazione di una nuova Pizza
    public Pizza create(Pizza pizza) {

        return pizzaRepository.save(pizza);
    }

    // Update di una pizza
    public Pizza update(Pizza pizza) {

        return pizzaRepository.save(pizza);
    }

    // Delete di una pizza

    // Delete di una pizza tramite id
    public void deleteById(Integer id) {

        Pizza pizza = getById(id);
        pizzaRepository.delete(pizza);
    }

}
