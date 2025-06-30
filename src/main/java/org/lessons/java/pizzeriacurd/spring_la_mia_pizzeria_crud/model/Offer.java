package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "Offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent(message = "start date cannot be in the future")
    @Column(name = "startDate")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "end date cannot be in the future")
    @Column(name = "endDate")
    private LocalDate endDate;

    @NotBlank(message = "title cannot be null")
    @Column(name = "title")
    private String title;

    // aggiunta della relazione molti a uno da offerte a pizza
    // aggiungiamo la colonna per collegare offerte alla singola pizza e
    // e informiamo che la nostra colonna pizza_id non può essere null
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    // getter + setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
