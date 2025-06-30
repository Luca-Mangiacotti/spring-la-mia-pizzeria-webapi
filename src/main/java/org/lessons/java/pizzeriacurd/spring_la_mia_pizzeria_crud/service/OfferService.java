package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    // Index
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    // Show
    public Offer getById(Integer id) {
        return offerRepository.findById(id).get();
    }

    // Create
    public Offer create(Offer offer) {
        return offerRepository.save(offer);
    }

    // Update
    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    // Delete By Id
    public void deleteById(Integer id) {
        offerRepository.deleteById(id);
    }

    // Delete
    public void delete(Offer offer) {
        offerRepository.deleteById(offer.getId());
    }

}
