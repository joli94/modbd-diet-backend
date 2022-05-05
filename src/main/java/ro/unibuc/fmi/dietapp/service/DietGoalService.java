package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.DietGoal;
import ro.unibuc.fmi.dietapp.repository.DietGoalRepository;

import java.util.List;

@Service
public class DietGoalService {
    private final DietGoalRepository dietGoalRepository;

    public DietGoalService(DietGoalRepository dietGoalRepository) {
        this.dietGoalRepository = dietGoalRepository;
    }

    public List<DietGoal> findAll() {
        return dietGoalRepository.findAll();
    }

    public DietGoal findById(Long id) {
        return dietGoalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The goal diet with this id doesn't exist in the database!")
        );
    }
}
