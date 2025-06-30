package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {

    // con questi comandi Hibernate ci assicura di ottenere un id autoincrementale
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nome")
    private String name;

    @NotBlank
    @Lob
    @Column(name = "descrizione")
    private String description;

    @NotBlank
    @Column(name = "foto")
    private String foto;

    @NotNull
    @Column(name = "prezzo")
    private BigDecimal price;

    // Aggiunta della relazione uno a molti da pizza a offerte
    // implementiamo il cascade che nell'effettivo elimina tutti i record collegati
    // al nostro elemento
    // in questo caso andremo ad eliminare tutte le offers collegate ad una pizza
    // quando la eliminiamo
    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Offer> offers;

    // aggiunta della relazione many to many con la tabella ingredients
    @ManyToMany
    @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    // getter & setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("Pizza %s, descrizione: %s, prezzo: %s €", this.name, this.description, this.price);
    }
}
