package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT c.*, ea.first_name, ea.last_name, ea.target, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id\n" +
            "UNION ALL \n" +
            "SELECT c.*, we.first_name, we.last_name, we.target, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id", nativeQuery = true)
    //TODOȘ QUERY-urile celelalte
    List<User> findAll();

    List<User> findByCountryId(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
