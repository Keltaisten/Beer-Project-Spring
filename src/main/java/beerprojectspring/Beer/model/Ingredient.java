package beerprojectspring.Beer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Ingredient {

    private String name;
    private double ratio;

    public Ingredient(String name) {
        this.name = name;
    }

}
