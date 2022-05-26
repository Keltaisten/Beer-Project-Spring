package beerprojectspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
    private Long beerId;
    private String id;
    private String name;
    private String brand;
    private String type;
    private int price;
    private double alcohol;
    private List<Ingredient> ingredients = new ArrayList<>();
//    private double waterIngredient;
}
