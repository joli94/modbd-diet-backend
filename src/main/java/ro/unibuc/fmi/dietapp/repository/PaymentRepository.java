package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Billing;
import ro.unibuc.fmi.dietapp.model.Payment;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT * FROM payment_eaeu UNION ALL SELECT * FROM payment_eaeu", nativeQuery = true)
    List<Payment> findAll();

    @Query(value = "SELECT * FROM (SELECT * FROM payment_eaeu UNION ALL SELECT * FROM payment_eaeu) WHERE payment_id = ?", nativeQuery = true)
    Optional<Payment> findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO payments (payment_total, payment_date, user_user_id) " +
            "VALUES (:total, :date, :user_id)", nativeQuery = true)
    void create(@Param("total") Long diet,
                @Param("date") LocalDate payment,
                @Param("user_id") Long user_id);

}
