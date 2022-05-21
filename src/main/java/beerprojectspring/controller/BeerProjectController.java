package beerprojectspring.controller;

import beerprojectspring.model.BeerDto;
import beerprojectspring.service.BeerIngredientService;
import beerprojectspring.service.BeerProjectService;
import beerprojectspring.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beers")
@Tag(name = "Operations on beers")
public class BeerProjectController {

    private BeerProjectService bService;
    private IngredientService iService;
    private BeerIngredientService bIService;

    public BeerProjectController(BeerProjectService service, IngredientService iService, BeerIngredientService bIService) {
        this.bService = service;
        this.iService = iService;
        this.bIService = bIService;
    }

    @GetMapping
    public List<BeerDto> listBeers(@RequestParam Optional<String> prefix) {
        return bService.getBeers(prefix);
    }

    @GetMapping("/{id}")
    public BeerDto findBeerById(@PathVariable("id") long id) {
        return bService.getBeerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a beer")
    @ApiResponse(responseCode = "201", description = "beer has been created")
    public BeerDto createBeer(@Valid @RequestBody CreateBeerCommand command) {
//        return service.createBeer(command);
        return bIService.createBeerWithIngredients(command);
    }
}
