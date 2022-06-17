package beerprojectspring.Webshop.dto;

import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.model.Beer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWebshopCommand {
    @NotBlank
    @Schema(description = "name of the webshop", example = "Cool Beers")
    private String name;
    @Email(message = "E-mail address must be valid!")
    @Schema(description = "e-mail address", example = "john.doe1700@gmail.com")
    private String emailAddress;
    @Schema(description = "list of the selling beers")
    private List<CreateBeerCommand> beers = new ArrayList<>();
}
