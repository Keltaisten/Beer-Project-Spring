package beerprojectspring.Webshop.service;

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
//        List<Webshop> webshops = webshopRepository.findAllWithBeers();
        List<Webshop> webshops = webshopRepository.findAll();
//        System.out.println(webshops.get(0));
//        System.out.println(webshops.get(1));
        return webshops.stream()
                .map(shop -> modelMapper.map(shop, WebshopDto.class))
//                .map(shop-> new WebshopDto(shop.getName(), shop.getEmailAddress(),
//                        shop.getBeers().stream()
//                                .map(beer->modelMapper.map(beer, BeerWebshopDto.class))
//                                .collect(Collectors.toList())))
//                .map(shop-> new WebshopDto(shop.getName(), shop.getEmailAddress(),
//                        shop.getBeers().stream()
//                                .map(Beer::getName)
//                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public WebshopDto createWebshop(CreateWebshopCommand command) {
        Webshop webshop = new Webshop(
                command.getName(),
                command.getEmailAddress());
        webshopRepository.save(webshop);
        return modelMapper.map(webshop, WebshopDto.class);
    }
}
