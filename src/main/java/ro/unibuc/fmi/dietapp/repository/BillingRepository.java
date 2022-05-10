package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Billing;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByUserId(Long id);

    @Query(value = "INSERT INTO billings(diet_diet_id, payment_payment_id, user_user_id) values (:billing.diet.id, :billing.payment.id, :billing.user.id)", nativeQuery = true)
    Billing save(@Param("billing") Billing billing );
}
