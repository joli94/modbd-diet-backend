package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.DietType;


@Repository
public interface DietTypeRepository extends JpaRepository<DietType, Long> {
}
