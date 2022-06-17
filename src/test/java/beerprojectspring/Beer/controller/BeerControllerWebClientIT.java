package beerprojectspring.Beer.controller;

import beerprojectspring.Beer.dto.BeerDto;
import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.dto.CreateIngredientCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from beers_webshops","delete from beer_ingredients","delete from beers","delete from webshops"})
class BeerControllerWebClientIT {

    @Autowired
    WebTestClient webTestClient;

    BeerDto beerDto;

    @BeforeEach
    void init(){
        beerDto = webTestClient.post()
                .uri("api/beers")
                .bodyValue(new CreateBeerCommand(
                        "Beer Sans Corn",
                        "Beer Sans Brewery",
                        "Corn",
                        910,
                        0.129,
                        Arrays.asList(
                                new CreateIngredientCommand("salt",0.004),
                                new CreateIngredientCommand("sugar",0.027),
                                new CreateIngredientCommand("barley",0),
                                new CreateIngredientCommand("wheat",0),
                                new CreateIngredientCommand("corn",0.221)
                        )))
                .exchange()
                .expectBody(BeerDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateBeer(){
        BeerDto actual = webTestClient.post()
                .uri("api/beers")
                .bodyValue(new CreateBeerCommand(
                        "Beer Sans Wheat",
                        "Beer Sans Brewery",
                        "Wheat",
                        1500,
                        0.2,
                        Arrays.asList(
                                new CreateIngredientCommand("salt",0.004),
                                new CreateIngredientCommand("sugar",0.027),
                                new CreateIngredientCommand("barley",0),
                                new CreateIngredientCommand("wheat",0.215),
                                new CreateIngredientCommand("corn",0.021)
                        )))
                .exchange()
                .expectBody(BeerDto.class)
                .returnResult().getResponseBody();
        assertThat(actual.getBrand()).isEqualTo("Beer Sans Brewery");


    }

    @Test
    void testGetBeerBrands(){
        webTestClient.post()
                .uri("api/beers")
                .bodyValue(new CreateBeerCommand(
                        "Beer Sans Wheat",
                        "Beer Sans White",
                        "Wheat",
                        1500,
                        0.2,
                        Arrays.asList()))
                .exchange()
                .expectBody(BeerDto.class);

        webTestClient.get()
                .uri("api/beers/brands")
                .exchange()
                .expectBody(TreeSet.class)
                .value(b->assertThat(b).containsExactly("Beer Sans Brewery","Beer Sans White"));

    }
}