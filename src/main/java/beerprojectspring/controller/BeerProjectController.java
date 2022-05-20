package beerprojectspring.controller;

import beerprojectspring.model.BeerDto;
import beerprojectspring.service.BeerProjectService;
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

    private BeerProjectService service;

    public BeerProjectController(BeerProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<BeerDto> listBeers(@RequestParam Optional<String> prefix) {
        return service.getBeers(prefix);
    }

    @GetMapping("/{id}")
    public BeerDto findBeerById(@PathVariable("id") long id) {
        return service.getBeerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a beer")
    @ApiResponse(responseCode = "201", description = "beer has been created")
    public BeerDto createBeer(@Valid @RequestBody CreateBeerCommand command) {
        return service.createBeer(command);
    }
}
