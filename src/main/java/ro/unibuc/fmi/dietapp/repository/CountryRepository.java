package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Country;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value = "SELECT * FROM country_eaeu UNION ALL SELECT * FROM country_weeu", nativeQuery = true)
    List<Country> findAll();

    @Query(value = "SELECT * FROM (SELECT * FROM country_eaeu UNION ALL SELECT * FROM country_weeu) WHERE country_id = ?", nativeQuery = true)
    Optional<Country> findById(Long id);
}
