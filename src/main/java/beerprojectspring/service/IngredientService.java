package beerprojectspring.service;

import beerprojectspring.controller.CreateBeerCommand;
import beerprojectspring.controller.CreateIngredientCommand;
import beerprojectspring.model.Beer;
import beerprojectspring.model.Ingredient;
import beerprojectspring.model.IngredientDto;
import beerprojectspring.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IngredientService {

    private ModelMapper modelMapper;

    private IngredientRepository repository;

    public IngredientService(ModelMapper modelMapper, IngredientRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public List<IngredientDto> getIngredients(Optional<String> prefix) {
        return repository.findAll().stream()
                .filter(i -> prefix.isEmpty() || i.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(ingr -> modelMapper.map(ingr, IngredientDto.class))
                .collect(Collectors.toList());
    }

    public IngredientDto getIngredientById(long id) {
        return modelMapper.map(repository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Can not find ingredient with id: " + id)),
                IngredientDto.class);
    }

    public IngredientDto createIngredient(CreateIngredientCommand command) {
        Ingredient ingredient = new Ingredient(command.getName());
//        command.getIngredients().forEach(ingredient -> ingredient.setBeer(beer));
//        command.getIngredients().stream().map(i->i.setBeer(beer));
        repository.save(ingredient);
//        command.getIngredients().forEach(ingredient -> ingredient.setBeerId(beer.getBeerId()));
//        ingredientRep.saveAll(command.getIngredients());
//        ingredientRep.save(command.getIngredients());
        log.info("Ingredient has been created");
        log.debug("Ingredient has been created with name {}", command.getName());
        return modelMapper.map(ingredient, IngredientDto.class);
    }
}
