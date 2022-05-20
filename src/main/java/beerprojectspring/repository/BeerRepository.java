package beerprojectspring.repository;

import beerprojectspring.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
