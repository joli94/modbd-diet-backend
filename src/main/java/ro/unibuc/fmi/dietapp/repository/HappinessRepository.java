package ro.unibuc.fmi.dietapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.dietapp.model.Happiness;

import java.util.List;

@Repository
public interface HappinessRepository extends JpaRepository<Happiness, Long> {
    List<Happiness> findByUserId(Long id);
}
