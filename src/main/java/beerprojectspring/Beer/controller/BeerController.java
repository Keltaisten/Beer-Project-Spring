package beerprojectspring.Beer.controller;

import beerprojectspring.Beer.dto.BeerDto;
import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.dto.CreateIngredientCommand;
import beerprojectspring.Beer.service.BeerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
@Tag(name = "Operations on beers")
@AllArgsConstructor
public class BeerController {

    private BeerService beerService;

    @GetMapping
    public List<BeerDto> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping("/{id}")
    public BeerDto getBeerById(@PathVariable("id") long id){
        return beerService.getBeerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a beer")
    @ApiResponse(responseCode = "201", description = "beer has been created")
    public BeerDto createNewBeer(@Valid @RequestBody CreateBeerCommand createBeerCommand) {
        return beerService.createNewBeer(createBeerCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable("id") long id) {
        beerService.deleteBeerById(id);
    }

    @PutMapping("/{id}")
    public BeerDto updateBeerByIdWithWebshop(@PathVariable("id") long beerId, long webshopId){
        return beerService.updateBeerByIdWithWebshop(beerId, webshopId);
    }

    @PostMapping("/{id}/ingredients")
    public BeerDto addIngredientsById(@PathVariable("id") long id, @Valid @RequestBody List<CreateIngredientCommand> ingredientCommands){
        return beerService.addIngredientsById(id, ingredientCommands);
    }
}
