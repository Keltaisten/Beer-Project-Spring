package beerprojectspring.Beer.repository;

import beerprojectspring.Beer.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findBeersByBrandAndType(@Param("brand") Optional<String> brand, @Param("type") Optional<String> type);

    @Query("select b.brand from Beer b")
    Set<String> getAllBrands();
}
