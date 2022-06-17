package beerprojectspring.Beer.model;

import beerprojectspring.Webshop.model.Webshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "beer_name")
    private String name;
    private String brand;
    private String type;
    private int price;
    private double alcohol;
    @ElementCollection
    private List<Ingredient> ingredients = new ArrayList<>();
//    @Transient
//    private double waterIngredient;
    @ManyToMany/*(fetch = FetchType.EAGER)*/
//    @JoinTable(name = "webshop_beers")
    private List<Webshop> webshops = new ArrayList<>();

    public Beer(String name) {
        this.name = name;
    }

    public Beer(String name, String brand, String type, int price, double alcohol) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.alcohol = alcohol;
    }

    public Beer(String name, String brand, String type, int price, double alcohol, List<Ingredient> ingredients) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.alcohol = alcohol;
        this.ingredients = ingredients;
    }

    public Beer(Long id, String name, String brand, String type, int price, double alcohol) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.alcohol = alcohol;
    }

    //    public void addWebshop(Webshop webshop) {
//        webshops.add(webshop);
//    }

    public void addIngredients(Ingredient ingredient) {
        ingredients.add(ingredient);
//        ingredient.setBeer(this);
    }
}