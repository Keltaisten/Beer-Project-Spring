package beerprojectspring.Beer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CreateIngredientCommand {

    @Schema(description = "name of the ingredient", example = "corn")
    @NotBlank(message = "name can not be blank")
    private String name;
    @PositiveOrZero
    @DecimalMax(value = "0.1",message = "ratio can not be more than 0.1")
    private double ratio;
}
