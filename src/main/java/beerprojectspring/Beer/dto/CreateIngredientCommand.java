package beerprojectspring.Beer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateIngredientCommand {

    @Schema(description = "name of the ingredient", example = "corn")
    @NotBlank(message = "name can not be blank")
    private String name;
    @PositiveOrZero
    @DecimalMax(value = "0.3", message = "ratio can not be more than 0.3")
    private double ratio;
}
