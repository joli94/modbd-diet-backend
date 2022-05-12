package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Billing;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    @Query(value = "SELECT * FROM billing_eaeu UNION ALL SELECT * FROM billing_weeu", nativeQuery = true)
    List<Billing> findAll();

    @Query(value = "SELECT * FROM (SELECT * FROM billing_eaeu UNION ALL SELECT * FROM billing_weeu) WHERE user_user_id = ?", nativeQuery = true)
    List<Billing> findByUserId(Long id);

    @Query(value = "SELECT * FROM (SELECT * FROM billing_eaeu UNION ALL SELECT * FROM billing_weeu) WHERE billing_id = ?", nativeQuery = true)
    Optional<Billing> findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO billings (diet_diet_id, user_user_id) " +
            "VALUES (:diet, :user_id)", nativeQuery = true)
    void create(@Param("diet") Long diet,
                @Param("user_id") Long user_id);
}
