package beerprojectspring.Webshop.controller;

import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import beerprojectspring.Webshop.dto.WebshopDto;
//import beerprojectspring.service.IngredientService;
import beerprojectspring.Webshop.service.WebshopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/webshops")
@Tag(name = "Operations on webshops")
@AllArgsConstructor
public class WebshopController {

    private WebshopService webshopService;

    @GetMapping
    public List<WebshopDto> listWebshops(@RequestParam Optional<String> prefix) {
        return webshopService.getWebshops(prefix);
    }

    @PostMapping
    public WebshopDto createWebshop(@RequestBody CreateWebshopCommand createWebshopCommand) {
        return webshopService.createWebshop(createWebshopCommand);
    }


}
