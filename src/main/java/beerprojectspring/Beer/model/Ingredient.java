package beerprojectspring.Beer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
//@Table(name = "ingredients")
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
