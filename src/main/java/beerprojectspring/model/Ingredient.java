package beerprojectspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double ratio;
    @ManyToOne
    @JoinColumn(name = "beer_object_id")
    private Beer beer;

//    @JoinColumn(name = "beer_id")
//    private Long beerId;


    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(String name, double ratio) {
        this.name = name;
        this.ratio = ratio;
    }
}
