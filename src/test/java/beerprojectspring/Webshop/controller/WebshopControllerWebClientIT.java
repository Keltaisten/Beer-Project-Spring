package beerprojectspring.Webshop.controller;

import beerprojectspring.Beer.dto.CreateBeerCommand;
import beerprojectspring.Beer.dto.CreateIngredientCommand;
import beerprojectspring.Webshop.dto.CreateWebshopCommand;
import beerprojectspring.Webshop.dto.WebshopDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from beers_webshops","delete from beer_ingredients","delete from beers","delete from webshops"})
class WebshopControllerWebClientIT {

    @Autowired
    WebTestClient webTestClient;

    WebshopDto webshopDto;

    @BeforeEach
    void init() {
        webshopDto = webTestClient.post()
                .uri("api/webshops")
//                .bodyValue(new CreateWebshopCommand())
                .bodyValue(new CreateWebshopCommand("Cool Beers", "john.doe@gmail.com",
                        Arrays.asList(
                                new CreateBeerCommand(
                                        "Beer Sans Corn",
                                        "Beer Sans Brewery",
                                        "Corn",
                                        910,
                                        0.129,
                                        Arrays.asList(
                                                new CreateIngredientCommand("salt", 0.004),
                                                new CreateIngredientCommand("sugar", 0.027),
                                                new CreateIngredientCommand("barley", 0),
                                                new CreateIngredientCommand("wheat", 0),
                                                new CreateIngredientCommand("corn", 0.221)
                                        ))
                        )))
                .exchange()
                .expectBody(WebshopDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateWebshop() {
        WebshopDto actual = webTestClient.post()
                .uri("api/webshops")
//                .bodyValue(new CreateWebshopCommand())
                .bodyValue(new CreateWebshopCommand("Awesome Beers", "jane.doe@gmail.com",
                        Arrays.asList(
                                new CreateBeerCommand(
                                        "Beer Sans Corn",
                                        "Beer Sans Brewery",
                                        "Corn",
                                        910,
                                        0.129,
                                        Arrays.asList(
                                                new CreateIngredientCommand("salt", 0.004),
                                                new CreateIngredientCommand("sugar", 0.027),
                                                new CreateIngredientCommand("barley", 0),
                                                new CreateIngredientCommand("wheat", 0),
                                                new CreateIngredientCommand("corn", 0.221)
                                        ))
                        )))
                .exchange()
                .expectBody(WebshopDto.class)
                .returnResult().getResponseBody();
        assertThat(actual.getEmailAddress()).isEqualTo("jane.doe@gmail.com");
    }

    @Test
    void getAllWebshops(){
        webTestClient.post()
                .uri("api/webshops")
//                .bodyValue(new CreateWebshopCommand())
                .bodyValue(new CreateWebshopCommand("Long Beers", "jane.doe@gmail.com",
                        Arrays.asList(
                                new CreateBeerCommand(
                                        "Beer Sans Corn",
                                        "Beer Sans Brewery",
                                        "Corn",
                                        910,
                                        0.129,
                                        Arrays.asList(
//                                                new CreateIngredientCommand("salt", 0.004),
//                                                new CreateIngredientCommand("sugar", 0.027),
//                                                new CreateIngredientCommand("barley", 0),
//                                                new CreateIngredientCommand("wheat", 0),
//                                                new CreateIngredientCommand("corn", 0.221)
                                        ))
                        )))
                .exchange();
        webTestClient.get()
                .uri("api/webshops")
                .exchange()
                .expectBodyList(WebshopDto.class)
                .hasSize(2)
                .contains(new WebshopDto("Cool Beers","john.doe@gmail.com"));
    }
}