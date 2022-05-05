package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.Diet;
import ro.unibuc.fmi.dietapp.repository.DietRepository;

import java.util.List;

@Service
public class DietService {

    private final DietRepository dietRepository;

    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    public List<Diet> findAll() {
        return dietRepository.findAll();
    }

    public List<Diet> findByGoalId(Long id) {
        return dietRepository.findByDietGoalId(id);
    }

    public List<Diet> findByTypeId(Long id) {
        return dietRepository.findByDietTypeId(id);
    }

    public Diet findById(Long id) {
        return dietRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The diet with this id doesn't exist in the database!")
        );
    }

    public Diet update(Diet diet) {
        if (dietRepository.existsById(diet.getId())) {
            return dietRepository.save(diet);
        } else {
            throw new EntityNotFoundException(String.format("There is no diet with id=%s in the database!", diet.getId().toString()));
        }

    }

    public void delete(Long id) {
        dietRepository.delete(findById(id));
    }
}
