package beerprojectspring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateIngredientCommand {

    @Schema(description = "name of the ingredient", example = "corn")
    @NotBlank(message = "name can not be blank")
    private String name;
}
