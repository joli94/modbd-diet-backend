package ro.unibuc.fmi.dietapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.DietPlan;
import ro.unibuc.fmi.dietapp.model.Food;
import ro.unibuc.fmi.dietapp.model.FoodCategory;
import ro.unibuc.fmi.dietapp.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @Mock
    private FoodRepository repository;

    @Mock
    private DietPlanService dietPlanService;

    @InjectMocks
    private FoodService service;

    private Food expected;
    private List<DietPlan> expectedDietPlanList;

    @BeforeEach
    void setUp() {
        expected = Food.builder()
                .id(1L)
                .name("pastrav la gratar")
                .calories(150L)
                .foodCategory(FoodCategory.builder().id(1L).name("lunch").build())
                .build();

        DietPlan expectedDietPlan = DietPlan.builder().id(1L).build();

        expectedDietPlanList = new ArrayList<>();
        expectedDietPlanList.add(expectedDietPlan);

        expected.setDietPlanList(expectedDietPlanList);
    }

    @Test
    @DisplayName("Find all foods - happy flow")
    public void test_findAllFoods_happyFlow() {
        List<Food> foodList = new ArrayList<>();
        foodList.add(expected);

        when(repository.findAll()).thenReturn(foodList);

        List<Food> result = service.findAll();

        assertEquals(foodList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCalories(), result.stream().findFirst().get().getCalories());
        assertEquals(expected.getFoodCategory(), result.stream().findFirst().get().getFoodCategory());
        assertEquals(expected.getDietPlanList(), result.stream().findFirst().get().getDietPlanList());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find a food by id - happy flow")
    public void test_findFoodById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Food result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCalories(), result.getCalories());
        assertEquals(expected.getFoodCategory(), result.getFoodCategory());
        assertEquals(expected.getDietPlanList(), result.getDietPlanList());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a food by id - id doesn't exist in the database")
    public void test_findFoodById_throwsEntityNotFoundException_whenFoodNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The food with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The food with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a food by food category id - happy flow")
    public void test_findFoodByFoodCategoryId_happyFlow() {
        Long id = expected.getFoodCategory().getId();

        List<Food> foodList = new ArrayList<>();
        foodList.add(expected);

        when(repository.findByFoodCategoryId(id)).thenReturn(foodList);

        List<Food> result = service.findByFoodCategory(id);

        assertEquals(foodList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCalories(), result.stream().findFirst().get().getCalories());
        assertEquals(expected.getFoodCategory(), result.stream().findFirst().get().getFoodCategory());
        assertEquals(expected.getDietPlanList(), result.stream().findFirst().get().getDietPlanList());

        verify(repository).findByFoodCategoryId(id);
    }

    @Test
    @DisplayName("Find a food by diet id - happy flow")
    public void test_findFoodByDietId_happyFlow() {
        Long id = expected.getDietPlanList().stream().findFirst().get().getId();
        Long foodId = expected.getId();

        List<Food> foodList = new ArrayList<>();
        foodList.add(expected);

        when(dietPlanService.findByDiet(id)).thenReturn(expectedDietPlanList);
        when(repository.findById(foodId)).thenReturn(Optional.of(expected));

        List<Food> result = service.findByDiet(id);

        assertEquals(foodList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCalories(), result.stream().findFirst().get().getCalories());
        assertEquals(expected.getFoodCategory(), result.stream().findFirst().get().getFoodCategory());
        assertEquals(expected.getDietPlanList(), result.stream().findFirst().get().getDietPlanList());

        verify(dietPlanService).findByDiet(id);
    }
}