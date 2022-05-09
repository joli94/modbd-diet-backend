package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT  * FROM users@bd_eaeu UNION ALL SELECT * FROM users@bd_weeu", nativeQuery = true)
    //TODO: de pus sinonimele
    List<User> findAll();

    List<User> findByCountryId(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
