package beerprojectspring.service;

import beerprojectspring.controller.CreateBeerCommand;
import beerprojectspring.model.Beer;
import beerprojectspring.model.BeerDto;
import beerprojectspring.model.Ingredient;
import beerprojectspring.repository.BeerRepository;
import beerprojectspring.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerIngredientService {

    private ModelMapper modelMapper;

    private BeerRepository beerRepository;

    private IngredientRepository ingredientRepository;

    public BeerIngredientService(ModelMapper modelMapper, BeerRepository beerRepository, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.beerRepository = beerRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public BeerDto createBeerWithIngredients(CreateBeerCommand command) {
        Beer beer = new Beer(command.getId(),command.getName(), command.getBrand(), command.getType(), command.getPrice(),command.getAlcohol());
        System.out.println(beer.getBeerId());
        beerRepository.save(beer);
        System.out.println(beer.getBeerId());
        List<Ingredient> ingredients = command.getIngredients();
        System.out.println(ingredients);
        beer.addIngredientsFromList(command.getIngredients());
        ingredientRepository.saveAll(command.getIngredients());
        return modelMapper.map(beer,BeerDto.class);
    }
}
