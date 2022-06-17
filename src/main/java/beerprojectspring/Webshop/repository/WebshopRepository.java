package beerprojectspring.Webshop.repository;

import beerprojectspring.Webshop.model.Webshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebshopRepository extends JpaRepository<Webshop, Long> {

//    @Query("select s from Webshop s join webshop_beers on Webshop.id = webshop_beers.webshops_id join Beer on Beer.id = webshop_beers.beer_id")
    @Query("select s from Webshop s left join fetch s.beers")
    List<Webshop> findAllWithBeers();



//    List<Webshop> findWebshopsWithBeer(){
//
//    }
}
