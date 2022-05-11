package ro.unibuc.fmi.dietapp.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Weight;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    @Query(value = "SELECT * FROM weight_eaeu UNION ALL SELECT * FROM weight_weeu", nativeQuery = true)
    List<Weight> findAll();

    @Query(value = "SELECT * FROM (SELECT * FROM weight_eaeu UNION ALL SELECT * FROM weight_weeu) WHERE user_user_id = ?", nativeQuery = true)
    List<Weight> findByUserId(Long id);

    @Query(value = "SELECT * FROM (SELECT * FROM weight_eaeu UNION ALL SELECT * FROM weight_weeu) WHERE weight_id = ?", nativeQuery = true)
    Optional<Weight> findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO weights(weight_date, weight_number, user_user_id) " +
            "VALUES (:date, :value, :user_id)", nativeQuery = true)
    void create(@Param("date") LocalDateTime date,
                @Param("value") Double value,
                @Param("user_id") Long user_id);
}