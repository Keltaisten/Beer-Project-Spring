package beerprojectspring.Webshop.controller;

import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import beerprojectspring.Webshop.dto.WebshopDto;
import beerprojectspring.service.BeerWebshopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/webshops")
@Tag(name = "Operations on webshops")
@AllArgsConstructor
public class WebshopController {

    private BeerWebshopService service;

    @GetMapping
    public List<WebshopDto> listWebshops(@RequestParam Optional<String> prefix) {
        return service.getWebshops(prefix);
    }

    @PostMapping
    public WebshopDto createWebshop(@RequestBody CreateWebshopCommand createWebshopCommand) {
        return service.createWebshop(createWebshopCommand);
    }

    @PutMapping("/{id}/beers")
    public WebshopDto updateWebshopWithBeerById(@PathVariable("id") long id, @RequestParam long beerId){
        return service.updateWebshopWithBeerById(id,beerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable("id") long id) {
        service.deleteWebshopById(id);
    }

}
