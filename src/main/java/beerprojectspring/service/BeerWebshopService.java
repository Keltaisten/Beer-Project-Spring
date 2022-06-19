package beerprojectspring.service;

import beerprojectspring.Beer.dto.CreateIngredientCommand;
import beerprojectspring.Beer.dto.IngredientDto;
import beerprojectspring.Beer.exception.BeerNotFoundException;
import beerprojectspring.Beer.model.Beer;
import beerprojectspring.Beer.dto.BeerDto;
import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.model.Ingredient;
import beerprojectspring.Beer.repository.BeerRepository;
import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import beerprojectspring.Webshop.dto.WebshopDto;
import beerprojectspring.Webshop.exception.WebshopNotFoundException;
import beerprojectspring.Webshop.model.Webshop;
import beerprojectspring.Webshop.repository.WebshopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BeerWebshopService {

    private BeerRepository beerRepository;
    private WebshopRepository webshopRepository;
    private ModelMapper modelMapper;

    public List<BeerDto> getAllBeers(Optional<String> brand, Optional<String> type) {
//        List<Beer> beers = beerRepository.findAll();
        List<Beer> beers = beerRepository.findBeersByBrandAndType(brand, type);
        return beers.stream()
                .map(beer -> modelMapper.map(beer, BeerDto.class))
                .collect(Collectors.toList());
    }

    public BeerDto createNewBeer(CreateBeerCommand createBeerCommand) {
        List<Ingredient> ingredients = new ArrayList<>();
        if (!createBeerCommand.getIngredients().isEmpty()) {
            ingredients = createBeerCommand.getIngredients().stream()
                    .map(i -> modelMapper.map(i, Ingredient.class))
                    .collect(Collectors.toList());
        }
        Beer beer = new Beer(
                createBeerCommand.getName(),
                createBeerCommand.getBrand(),
                createBeerCommand.getType(),
                createBeerCommand.getPrice(),
                createBeerCommand.getAlcohol()
                , ingredients
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

    private Beer findBeerById(long id) {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    public BeerDto getBeerById(long id) {
        return modelMapper.map(findBeerById(id),
                BeerDto.class);
    }

    public BeerDto updateBeerByIdWithWebshop(long beerId, long webshopId) {
        Beer beer = findBeerById(beerId);
        Webshop webshop = findWebshopById(webshopId);
        beer.addWebshop(webshop);
        webshop.addBeer(beer);
        return modelMapper.map(beer, BeerDto.class);
    }

    public BeerDto addIngredientsById(long id, List<CreateIngredientCommand> ingredientCommands) {
        Beer beer = findBeerById(id);
//        Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
        ingredientCommands.stream()
                .map(i -> modelMapper.map(i, Ingredient.class))
                .forEach(beer::addIngredients);
        return modelMapper.map(beer, BeerDto.class);
    }

    public Set<String> getAllBrands() {
        return beerRepository.getAllBrands();
    }

//    public BeerDto addWebshopToBeerById(long beerId, CreateWebshopCommand command) {
//        Beer beer = findBeerById(beerId);
//        Webshop webshop = new Webshop(
//                command.getName(),
//                command.getEmailAddress(),
//                List.of(beer));
//        beer.addWebshop(webshop);
//        return modelMapper.map(beer, BeerDto.class);
//    }

    public List<WebshopDto> getWebshops(Optional<String> prefix) {
        List<Webshop> webshops = webshopRepository.findAllWithBeers();
        return webshops.stream()
                .map(shop -> modelMapper.map(shop, WebshopDto.class))
                .collect(Collectors.toList());
    }

    public WebshopDto createWebshop(CreateWebshopCommand command) {
        List<Beer> beers = command.getBeers().stream().map(b -> modelMapper.map(b, Beer.class)).collect(Collectors.toList());
        Webshop webshop = new Webshop(
                command.getName(),
                command.getEmailAddress(),
                beers);
        beers.forEach(b -> b.addWebshop(webshop));
        webshopRepository.save(webshop);
        return modelMapper.map(webshop, WebshopDto.class);
    }

    public void deleteWebshopById(long id) {
        try {
            webshopRepository.deleteById(id);
        } catch (EmptyResultDataAccessException erdae) {
            throw new BeerNotFoundException(id);
        }
    }

    public BeerDto addOneIngredientsById(long id, CreateIngredientCommand command) {
        Beer beer = findBeerById(id);
        beer.addIngredients(new Ingredient(command.getName(), command.getRatio()));
        return modelMapper.map(beer, BeerDto.class);
    }

    public WebshopDto updateWebshopWithBeerById(long webshopId, long beerId) {
        Webshop webshop = findWebshopById(webshopId);
        Beer beer = findBeerById(beerId);
        webshop.addBeer(beer);
        beer.addWebshop(webshop);
        return modelMapper.map(webshop, WebshopDto.class);

    }

    private Webshop findWebshopById(long id) {
        return webshopRepository.findById(id)
                .orElseThrow(() -> new WebshopNotFoundException(id));
    }

    public List<IngredientDto> getIngredientsByBeerId(long id) {
        return null;
    }
}
