package beerprojectspring.Webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebshopDto {
    private String name;
    private String emailAddress;
    //    @JsonIgnore
//    private List<BeerWebshopDto> beers = new ArrayList<>();
    private List<String> beerName = new ArrayList<>();
}
