package beerprojectspring.Beer.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Ingredient {

    private String name;
    private double ratio;

}
