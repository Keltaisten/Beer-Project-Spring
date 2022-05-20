package beerprojectspring.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBeerCommand {

    @Schema(description = "name of the beer", example = "Soproni")
    @NotBlank(message = "name can not be blank")
    private String name;
}
