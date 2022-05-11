package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.User;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT c.*, ea.first_name, ea.last_name, ea.gender, ea.target, ea.username, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id\n" +
            "UNION ALL \n" +
            "SELECT c.*, we.first_name, we.last_name, we.target, we.gender, we.username, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id", nativeQuery = true)
    //TODOȘ QUERY-urile celelalte
    List<User> findAll();

    @Query(value = "SELECT c.*, ea.first_name, ea.last_name, ea.gender, ea.target, ea.username, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id", nativeQuery = true)
    List<User> findAllInEaEu();

    @Query(value = "SELECT c.*, we.first_name, we.last_name, we.gender, we.target, we.username, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id", nativeQuery = true)
    List<User> findAllInWeEu();

    @Query(value = "SELECT * FROM (" +
            "SELECT c.*, ea.first_name, ea.last_name, ea.gender, ea.target, ea.username, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id\n" +
            "UNION ALL \n" +
            "SELECT c.*, we.first_name, we.last_name, we.target, we.gender, we.username, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id) " +
            "WHERE country_country_id = ?", nativeQuery = true)
    List<User> findByCountryId(Long id);

    @Query(value = "SELECT * FROM (" +
            "SELECT c.*, ea.first_name, ea.last_name, ea.gender, ea.target, ea.username, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id\n" +
            "UNION ALL \n" +
            "SELECT c.*, we.first_name, we.last_name, we.target, we.gender, we.username, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id) " +
            "WHERE username = ?", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM (" +
            "SELECT c.*, ea.first_name, ea.last_name, ea.gender, ea.target, ea.username, ea.country_country_id\n" +
            "FROM users_confidential c, users_eaeu ea\n" +
            "WHERE c.user_id = ea.user_id\n" +
            "UNION ALL \n" +
            "SELECT c.*, we.first_name, we.last_name, we.target, we.gender, we.username, we.country_country_id\n" +
            "FROM users_confidential c, users_weeu we\n" +
            "WHERE c.user_id = we.user_id) " +
            "WHERE user_id = ?", nativeQuery = true)
    Optional<User> findById(Long id);

    @Modifying
    @Query(value = "INSERT INTO users_view(first_name, last_name, gender, target, username, country_country_id, birth_date, cnp, card_number, is_admin) " +
                   "VALUES (:first_name, :last_name, :gender, :target, :username, :country_id, :birth_date, :cnp, :card_number, :is_admin)", nativeQuery = true)
    void create (@Param("first_name") String first_name,
                 @Param("last_name") String last_name,
                 @Param("gender") String gender,
                 @Param("target") String target,
                 @Param("username") String username,
                 @Param("country_id") Long country_id,
                 @Param("birth_date") LocalDate birth_date,
                 @Param("cnp") String cnp,
                 @Param("card_number") String card_number,
                 @Param("is_admin") String is_admin);

    @Modifying
    @Transactional
    @Query(value =  "UPDATE users_view " +
                    "SET first_name = :first_name, " +
                    "last_name = :last_name," +
                    "gender = :gender, " +
                    "target = :target, " +
                    "cnp = :cnp, " +
                    "card_number = :card_number, " +
                    "is_admin = :is_admin " +
                    "WHERE user_id = :user_id", nativeQuery = true)
    void update (@Param("user_id") Long user_id,
                 @Param("first_name") String first_name,
                 @Param("last_name") String last_name,
                 @Param("gender") String gender,
                 @Param("target") String target,
                 @Param("cnp") String cnp,
                 @Param("card_number") String card_number,
                 @Param("is_admin") String is_admin);

    @Modifying
    @Transactional
    @Query(value =  "UPDATE users_view " +
            "SET is_admin = :is_admin " +
            "WHERE user_id = :user_id", nativeQuery = true)
    void changeAdmin (@Param("user_id") Long user_id,
                 @Param("is_admin") String is_admin);

}
