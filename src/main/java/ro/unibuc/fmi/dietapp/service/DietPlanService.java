package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.model.DietPlan;
import ro.unibuc.fmi.dietapp.repository.DietPlanRepository;

import java.util.List;

@Service
public class DietPlanService {
    private final DietPlanRepository repository;

    public DietPlanService(DietPlanRepository repository) {
        this.repository = repository;
    }

    public List<DietPlan> findByDiet(Long id) {
        return repository.findByDietId(id);
    }
}
