package beerprojectspring.Beer.controller;

import beerprojectspring.Beer.dto.BeerDto;
import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.dto.CreateIngredientCommand;
import beerprojectspring.Beer.dto.IngredientDto;
import beerprojectspring.service.BeerWebshopService;
import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/beers")
@Tag(name = "Operations on beers")
@AllArgsConstructor
public class BeerController {

    private BeerWebshopService beerService;

    @GetMapping
    public List<BeerDto> getAllBeers(@RequestParam Optional<String> brand, @RequestParam Optional<String> type) {
        return beerService.getAllBeers(brand,type);
    }

    @GetMapping("/{id}")
    public BeerDto getBeerById(@PathVariable("id") long id) {
        return beerService.getBeerById(id);
    }

    @GetMapping("/brands")
    public Set<String> getBeerBrands(){
        return beerService.getAllBrands();
    }

    @GetMapping("/{id}/ingredients")
    public List<IngredientDto> getIngredientsByBeerId(@PathVariable("id") long id){
        return beerService.getIngredientsByBeerId(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a beer")
    @ApiResponse(responseCode = "201", description = "beer has been created")
    public BeerDto createNewBeer(@Valid @RequestBody CreateBeerCommand createBeerCommand) {
        return beerService.createNewBeer(createBeerCommand);
    }

//    @PostMapping("/{id}/ingredients")
//    public BeerDto addIngredientsById(@PathVariable("id") long id, @Valid @RequestBody List<CreateIngredientCommand> ingredientCommands) {
//        return beerService.addIngredientsById(id, ingredientCommands);
//    }

    @PostMapping("/{id}/ingredients")
    public BeerDto addOneIngredientById(@PathVariable("id") long id, @Valid @RequestBody CreateIngredientCommand ingredientCommands) {
        return beerService.addOneIngredientsById(id, ingredientCommands);
    }

//    @PostMapping("/{id}/webshops")
//    public BeerDto addWebshopToBeerById(@PathVariable("id") long beerId, CreateWebshopCommand command) {
//        return beerService.addWebshopToBeerById(beerId, command);
//    }


    @PutMapping("/{id}/webshops")
    public BeerDto updateBeerByIdWithWebshop(@PathVariable("id") long beerId,@RequestParam long webshopId) {
        return beerService.updateBeerByIdWithWebshop(beerId, webshopId);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable("id") long id) {
        beerService.deleteBeerById(id);
    }
}
