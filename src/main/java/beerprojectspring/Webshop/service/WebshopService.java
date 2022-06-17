package beerprojectspring.Webshop.service;

import beerprojectspring.Beer.model.Beer;
import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import beerprojectspring.Webshop.model.Webshop;
import beerprojectspring.Webshop.dto.WebshopDto;
import beerprojectspring.Webshop.repository.WebshopRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class WebshopService {

    private WebshopRepository webshopRepository;
    private ModelMapper modelMapper;

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
}
