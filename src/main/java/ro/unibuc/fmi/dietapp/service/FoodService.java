package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.DietPlan;
import ro.unibuc.fmi.dietapp.model.Food;
import ro.unibuc.fmi.dietapp.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final DietPlanService dietPlanService;

    public FoodService(FoodRepository foodRepository, DietPlanService dietPlanService) {
        this.foodRepository = foodRepository;
        this.dietPlanService = dietPlanService;
    }

    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public List<Food> findByFoodCategory(Long id) {
        return foodRepository.findByFoodCategoryId(id);
    }

    public List<Food> findByDiet(Long id) {
        List<Food> foodList = new ArrayList<>();
        List<DietPlan> result = dietPlanService.findByDiet(id);

        result.forEach((food) -> foodList.add(findById(food.getId())));

        return foodList;
    }

    public Food findById(Long id) {
        return foodRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The food with this id doesn't exist in the database!")
        );
    }
}
