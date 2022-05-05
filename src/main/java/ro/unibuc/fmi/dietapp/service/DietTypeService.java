package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.DietType;
import ro.unibuc.fmi.dietapp.repository.DietTypeRepository;

import java.util.List;

@Service
public class DietTypeService {
    private final DietTypeRepository dietTypeRepository;

    public DietTypeService(DietTypeRepository dietTypeRepository) {
        this.dietTypeRepository = dietTypeRepository;
    }

    public List<DietType> findAll() {
        return dietTypeRepository.findAll();
    }

    public DietType findById(Long id) {
        return dietTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The type diet with this id doesn't exist in the database!")
        );
    }
}
