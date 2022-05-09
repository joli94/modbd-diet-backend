package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Country;
import ro.unibuc.fmi.dietapp.model.User;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value = "SELECT * FROM country_eaeu@bd_eaeu UNION ALL SELECT * FROM country_weeu@bd_weeu", nativeQuery = true)
        //TODO: de pus sinonimele
    List<Country> findAll();
}
