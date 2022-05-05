package ro.unibuc.fmi.dietapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.fmi.dietapp.model.Diet;
import ro.unibuc.fmi.dietapp.model.DietPlan;
import ro.unibuc.fmi.dietapp.model.Food;
import ro.unibuc.fmi.dietapp.repository.DietPlanRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DietPlanServiceTest {
    @Mock
    private DietPlanRepository repository;

    @InjectMocks
    private DietPlanService service;

    private DietPlan expected;

    @BeforeEach
    void setUp() {
        expected = DietPlan.builder()
                .id(1L)
                .diet(Diet.builder().id(1L).name("Dieta fulger").maximumCalories(1500L).price(500L).build())
                .food(Food.builder().id(1L).name("Pastrav la gratar").calories(150L).build())
                .build();
    }

    @Test
    @DisplayName("Find foods  by diet id - happy flow")
    public void test_findFoodsByDietId_happyFlow() {
        Long id = expected.getDiet().getId();

        List<DietPlan> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByDietId(id)).thenReturn(expectedList);

        List<DietPlan> result = service.findByDiet(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getDiet(), result.stream().findFirst().get().getDiet());
        assertEquals(expected.getFood(), result.stream().findFirst().get().getFood());

        verify(repository).findByDietId(id);
    }
}