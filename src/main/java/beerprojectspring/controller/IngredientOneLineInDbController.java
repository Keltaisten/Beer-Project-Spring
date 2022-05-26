//package beerprojectspring.controller;
//
//import beerprojectspring.model.IngredientDto;
//import beerprojectspring.model.IngredientOneLineInDbDto;
//import beerprojectspring.service.IngredientService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/ingredientsOneLine")
//@Tag(name = "Operations on ingredients")
//public class IngredientOneLineInDbController {
//
//    private IngredientService service;
//
//    public IngredientOneLineInDbController(IngredientService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<IngredientOneLineInDbDto> getIngredients(@RequestParam Optional<String> prefix) {
//        return service.getIngredients(prefix);
//    }
//
//    @GetMapping("/{id}")
//    public IngredientDto getIngredientById(@PathVariable("id") long id) {
//        return service.getIngredientById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @Operation(summary = "creates an ingredient")
//    @ApiResponse(responseCode = "201", description = "ingredient has been created")
//    public IngredientDto createIngredient(@Valid @RequestBody CreateIngredientCommand command) {
//        return service.createIngredient(command);
//    }
//}