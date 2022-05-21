package beerprojectspring.model;

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
    @Column(name = "beer_id")
    private Long beerId;
    private String id;
    @Column(name = "beer_name")
    private String name;
    private String brand;
    private String type;
    private int price;
    private double alcohol;

    @OneToMany(mappedBy = "beer")
    private List<Ingredient> ingredients = new ArrayList<>();
//    @Transient
//    private double waterIngredient;

    public Beer(String name) {
        this.name = name;
    }

    public Beer(String id, String name, String brand, String type, int price, double alcohol) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.alcohol = alcohol;
    }

    public Beer(String id, String name, String brand, String type, int price, double alcohol, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.alcohol = alcohol;
        this.ingredients = ingredients;
    }

    public void addIngredients(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setBeer(this);
    }

    public void addIngredientsFromList(List<Ingredient> ingr) {
        ingr.forEach(i->i.setBeer(this));
        ingredients.addAll(ingr);
    }
}
