package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.IngredientRepository;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Pizza;
import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientRepository ingredientRepository;

    // INDEX DEI PRODOTTI (INDEX)

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {

        // con questo comando è come fare una query SELECT * from pizzas e li trasforma
        // in una lista di tipo Pizza
        List<Pizza> pizzas;
        if (keyword != null) {
            pizzas = pizzaService.findAll(keyword);
        } else {
            pizzas = pizzaService.findAll(keyword = null);
        }
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("keyword", keyword);

        return "pizzas/index";
    }

    // DETTAGLIO DI UN PRODOTTO (SHOW)

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        // con questo comando è come fare una query SELECT * from pizzas where "id" =id
        Pizza pizza = pizzaService.getById(id);

        if (pizza == null) {
            return "rederict:/pizzas";
        }
        model.addAttribute("pizza", pizza);

        return "pizzas/detail";
    }

    // AGGIUNTA DI UN NUOVO PRODOTTO (CREATE)

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        // utilizziamo il bindingResult per far verificare al validatore se ci sono
        // stati errori di inserimento nel form
        // in caso ci fossero ci sarà un indirizzamento allo stesso form
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        // nel caso non ci siano errori possiamo salvare il nostro prodotto tramite la
        // repository con il comando .save
        pizzaService.create(pizzaForm);
        return "redirect:/pizzas";

    }

    // MODIFICA DI UN PRODOTTO (UPDATE)

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        // utilizziamo il bindingResult per far verificare al validatore se ci sono
        // stati errori di inserimento nel form
        // in caso ci fossero ci sarà un indirizzamento allo stesso form
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        // nel caso non ci siano errori possiamo salvare il nostro prodotto tramite la
        // repository con il comando .save
        pizzaService.update(pizzaForm);
        return "redirect:/pizzas/{id}";

    }

    // ELIMINAZIONE DI UN PRODOTTO (DELETE)
    // andando a prendere l'elemento tramite id dal db ci serviamo della repository
    // che utilizza il metodo
    // deleteById(idProdotto)

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }

    // metodo per restituire la lista di offerte su una pizza

    @GetMapping("/{id}/offer")
    public String offer(@PathVariable("id") Integer id, Model model) {

        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));
        model.addAttribute("offer", offer);

        return "offers/create-edit";
    }

    // metodo per restituire la lista di ingredienti su una pizza

    @GetMapping("/{id}/ingredient")
    public String ingredients(@PathVariable("id") Integer id, Model model) {

        List<Ingredient> ingredients = pizzaService.getById(id).getIngredients();
        model.addAttribute("ingredients", ingredients);

        return "ingredients/create-edit";

    }

}