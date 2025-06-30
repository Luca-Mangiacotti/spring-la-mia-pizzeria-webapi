package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.OfferRepository;
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
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository repository;

    // AGGIUNTA DI UNA NUOVA OFFERTA (CREATE)
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("offer", new Offer());
        return "offers/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer offerForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-edit";
        }

        repository.save(offerForm);
        return "redirect:/pizzas/" + offerForm.getPizza().getId();

    }

    // MODIFICA DELL'OFFERTA (UPDATE)

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "offers/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer offerForm, BindingResult bindingResult, Model model) {
        // utilizziamo il bindingResult per far verificare al validatore se ci sono
        // stati errori di inserimento nel form
        // in caso ci fossero ci sarà un indirizzamento allo stesso form
        if (bindingResult.hasErrors()) {
            return "offers/create-edit";
        }

        // nel caso non ci siano errori possiamo salvare il nostro prodotto tramite la
        // repository con il comando .save
        repository.save(offerForm);
        return "redirect:/pizzas/" + offerForm.getPizza().getId();

    }

    // ELIMINAZIONE DI UN'OFFERTA (DELETE)
    // andando a prendere l'elemento tramite id dal db ci serviamo della repository
    // che utilizza il metodo
    // deleteById(idProdotto)

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        // aggiungiamo .orElseThrow per gestire l'eccezione nel caso non esistesse
        // l'offerta
        Offer offer = repository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
        Integer pizzaId = offer.getPizza().getId();
        repository.deleteById(id);

        return "redirect:/pizzas/" + pizzaId;
    }

}
