package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
