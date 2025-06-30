package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // INDEX PER GLI INGREDIENTI
    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    // AGGIUNTA DI UN NUOVO INGREDIENTE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientRepository.save(ingredientForm);
        return "redirect:/ingredients";

    }

    // MODIFICA DELL'OFFERTA (UPDATE)

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult,
            Model model) {
        // utilizziamo il bindingResult per far verificare al validatore se ci sono
        // stati errori di inserimento nel form
        // in caso ci fossero ci sarà un indirizzamento allo stesso form
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        // nel caso non ci siano errori possiamo salvare il nostro prodotto tramite la
        // repository con il comando .save
        ingredientRepository.save(ingredientForm);
        return "redirect:/ingredients";

    }

    // ELIMINAZIONE DI UN'OFFERTA (DELETE)
    // andando a prendere l'elemento tramite id dal db ci serviamo della repository
    // che utilizza il metodo
    // deleteById(idProdotto)

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        // andiamo a salvare l'ingrediente da rimuovere tramite id in una variabile
        // Ingredient dalla nostra ingredientRepository
        // attraverso un forEach andiamo a chiedere di ciclare "per tutte lepizze che
        // hanno quell'ingrediente " ->rimuovi l'ingrediente
        // dalla pizza collegata
        // infine elimina attraverso la repository l'ingrediente dal DB
        Ingredient ingredientToRemove = ingredientRepository.findById(id).get();
        for (Pizza linkedPizza : ingredientToRemove.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToRemove);

        }
        ingredientRepository.delete(ingredientToRemove);

        return "redirect:/ingredients";
    }

}
