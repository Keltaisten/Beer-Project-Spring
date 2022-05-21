package beerprojectspring.controller;

import beerprojectspring.model.Ingredient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateBeerCommand {

    @Schema(description = "name of the beer", example = "Beer Sans Corn")
    @NotBlank(message = "name can not be blank")
    private String name;
    @Schema(description = "beer id of the beer", example = "bsc-1")
    private String id;
    @Schema(description = "brand of the beer", example = "Beer Sans Brewery")
    private String brand;
    @Schema(description = "type of the beer", example = "Corn")
    private String type;
    @Schema(description = "price of the beer", example = "910")
    private int price;
    @Schema(description = "alcohol of the beer", example = "0.129")
    private double alcohol;
    @Schema(description = "ingredients of the beer", example = "list")
    private List<Ingredient> ingredients = new ArrayList<>();
}
