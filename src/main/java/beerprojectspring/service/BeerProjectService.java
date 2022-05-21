package beerprojectspring.service;

import beerprojectspring.controller.CreateBeerCommand;
import beerprojectspring.controller.CreateIngredientCommand;
import beerprojectspring.model.Beer;
import beerprojectspring.model.BeerDto;
import beerprojectspring.model.IngredientDto;
import beerprojectspring.repository.BeerRepository;
import beerprojectspring.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BeerProjectService {

    private ModelMapper modelMapper;

//    private AtomicLong idGenerator = new AtomicLong();

    private BeerRepository beerRep;

    public BeerProjectService(ModelMapper modelMapper, BeerRepository beerRep) {
        this.modelMapper = modelMapper;
        this.beerRep = beerRep;
    }

    public List<BeerDto> getBeers(Optional<String> prefix) {
        return beerRep.findAll().stream()
                .filter(b -> prefix.isEmpty() || b.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(beer -> modelMapper.map(beer, BeerDto.class))
                .collect(Collectors.toList());
    }

    public BeerDto getBeerById(long id) {
        return modelMapper.map(beerRep.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Can not find beer with id: " + id)),
                BeerDto.class);
    }

//    public BeerDto createBeer(CreateBeerCommand command) {
//        Beer beer = new Beer(command.getName());
//        beerRep.save(beer);
//        log.info("Beer has been created");
//        log.debug("Location has been created with name {}", command.getName());
//        return modelMapper.map(beer, BeerDto.class);
//    }

    public BeerDto createBeer(CreateBeerCommand command) {
        Beer beer = new Beer(command.getId(),command.getName(), command.getBrand(), command.getType(), command.getPrice(),command.getAlcohol(),command.getIngredients());
//        command.getIngredients().forEach(ingredient -> ingredient.setBeer(beer));
//        command.getIngredients().stream().map(i->i.setBeer(beer));
        beerRep.save(beer);
//        command.getIngredients().forEach(ingredient -> ingredient.setBeerId(beer.getBeerId()));
//        ingredientRep.saveAll(command.getIngredients());
//        ingredientRep.save(command.getIngredients());
        log.info("Beer has been created");
        log.debug("Beer has been created with name {}", command.getName());
        return modelMapper.map(beer, BeerDto.class);
    }

//    public IngredientDto createIngredient(CreateIngredientCommand command){
//
//    }


}
