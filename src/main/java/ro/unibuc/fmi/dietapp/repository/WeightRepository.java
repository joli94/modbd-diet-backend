package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Weight;

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

    //TODO: create, update
}
