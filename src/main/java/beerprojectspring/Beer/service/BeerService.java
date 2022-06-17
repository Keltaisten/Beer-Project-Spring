package beerprojectspring.Beer.service;

import beerprojectspring.Beer.dto.CreateIngredientCommand;
import beerprojectspring.Beer.exception.BeerNotFoundException;
import beerprojectspring.Beer.model.Beer;
import beerprojectspring.Beer.dto.BeerDto;
import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.model.Ingredient;
import beerprojectspring.Beer.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BeerService {

    private BeerRepository beerRepository;
    private ModelMapper modelMapper;

    public List<BeerDto> getAllBeers() {
        List<Beer> beers = beerRepository.findAll();
        return beers.stream()
                .map(beer -> modelMapper.map(beer, BeerDto.class))
                .collect(Collectors.toList());
    }

    public BeerDto createNewBeer(CreateBeerCommand createBeerCommand) {
        Beer beer = new Beer(
                createBeerCommand.getName(),
                createBeerCommand.getBrand(),
                createBeerCommand.getType(),
                createBeerCommand.getPrice(),
                createBeerCommand.getAlcohol()
//                ,createBeerCommand.getIngredients()
        );
        log.info("Beer has been created");
        log.debug("Beer has been created with name {}", createBeerCommand.getName());
        beerRepository.save(beer);
        return modelMapper.map(beer, BeerDto.class);
    }

    public void deleteBeerById(long id) {
        try {
            beerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException erdae) {
            throw new BeerNotFoundException(id);
        }
    }

    public BeerDto getBeerById(long id) {
        return modelMapper.map(beerRepository.findById(id)
                        .orElseThrow(() -> new BeerNotFoundException(id)),
                BeerDto.class);
    }

    public BeerDto updateBeerByIdWithWebshop(long beerId, long webshopId) {
        BeerDto beer = getBeerById(beerId);

        return null;
    }

    public BeerDto addIngredientsById(long id, List<CreateIngredientCommand> ingredientCommands) {
        Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
        ingredientCommands.stream()
                .map(i -> modelMapper.map(i, Ingredient.class))
                .forEach(beer::addIngredients);
        return modelMapper.map(beer, BeerDto.class);
    }
}
