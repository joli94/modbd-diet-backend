package ro.unibuc.fmi.dietapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.fmi.dietapp.model.Food;
import ro.unibuc.fmi.dietapp.model.FoodIngredients;
import ro.unibuc.fmi.dietapp.model.Ingredient;
import ro.unibuc.fmi.dietapp.repository.FoodIngredientsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodIngredientsServiceTest {
    @Mock
    private FoodIngredientsRepository repository;

    @InjectMocks
    private FoodIngredientsService service;

    private FoodIngredients expected;

    @BeforeEach
    void setUp() {
        expected = FoodIngredients.builder()
                .id(1L)
                .food(Food.builder().id(1L).name("pastrav la gratar").calories(150L).build())
                .ingredient(Ingredient.builder().id(1L).name("pastrav").calories(150L).build())
                .build();
    }

    @Test
    @DisplayName("Find food ingredients by food id - happy flow")
    public void test_findFoodIngredientsByFoodId_happyFlow() {
        Long id = expected.getFood().getId();

        List<FoodIngredients> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByFoodId(id)).thenReturn(expectedList);

        List<FoodIngredients> result = service.findByFoodId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getFood(), result.stream().findFirst().get().getFood());
        assertEquals(expected.getIngredient(), result.stream().findFirst().get().getIngredient());

        verify(repository).findByFoodId(id);
    }
}