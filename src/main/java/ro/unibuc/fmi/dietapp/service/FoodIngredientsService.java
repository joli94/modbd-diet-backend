package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.model.FoodIngredients;
import ro.unibuc.fmi.dietapp.repository.FoodIngredientsRepository;

import java.util.List;

@Service
public class FoodIngredientsService {
    private final FoodIngredientsRepository foodIngredientsRepository;

    public FoodIngredientsService(FoodIngredientsRepository foodIngredientsRepository) {
        this.foodIngredientsRepository = foodIngredientsRepository;
    }

    public List<FoodIngredients> findByFoodId(Long id) {
        return foodIngredientsRepository.findByFoodId(id);
    }
}
