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

    @OneToMany(mappedBy = "beer", cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients = new ArrayList<>();
    @Transient
    private double waterIngredient;

    public Beer(String name) {
        this.name = name;
    }
}
