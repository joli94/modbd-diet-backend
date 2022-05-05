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
import ro.unibuc.fmi.dietapp.model.FoodIngredients;
import ro.unibuc.fmi.dietapp.model.Ingredient;
import ro.unibuc.fmi.dietapp.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {
    @Mock
    private IngredientRepository repository;

    @Mock
    private FoodIngredientsService foodIngredientsService;

    @InjectMocks
    private IngredientService service;

    private Ingredient expected;

    @BeforeEach
    void setUp() {
        expected = Ingredient.builder()
                .id(1L)
                .name("afine")
                .calories(61L)
                .build();
    }

    @Test
    @DisplayName("Find all ingredients - happy flow")
    public void test_findAllIngredients_happyFlow() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(expected);

        when(repository.findAll()).thenReturn(ingredientList);

        List<Ingredient> result = service.findAll();

        assertEquals(ingredientList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCalories(), result.stream().findFirst().get().getCalories());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find an ingredient by id - happy flow")
    public void test_findIngredientById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Ingredient result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCalories(), result.getCalories());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find an ingredient by id - id doesn't exist in the database")
    public void test_findIngredientById_throwsEntityNotFoundException_whenIngredientNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The ingredient with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The ingredient with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find all ingredients for a food by id - happy flow")
    public void test_findIngredientForFoodById_happyFlow() {
        Long id = 1L;

        FoodIngredients foodIngredients = FoodIngredients.builder().id(id).build();
        List<FoodIngredients> expectedList = new ArrayList<>();
        expectedList.add(foodIngredients);

        expected.setFoodIngredientsList(expectedList);

        when(foodIngredientsService.findByFoodId(id)).thenReturn(expectedList);
        when(repository.findById(id)).thenReturn(Optional.of(expected));

        List<Ingredient> result = service.findByFoodId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCalories(), result.stream().findFirst().get().getCalories());

        verify(repository).findById(any());
        verify(foodIngredientsService).findByFoodId(id);
    }

    @Test
    @DisplayName("Find all ingredients for a food by id - ingredient id doesn't exist in the database")
    public void test_findIngredientForFoodById_throwsEntityNotFoundException_whenIngredientNotFound() {
        Long id = 1L;

        FoodIngredients foodIngredients = FoodIngredients.builder().id(id).build();
        List<FoodIngredients> expectedList = new ArrayList<>();
        expectedList.add(foodIngredients);

        expected.setFoodIngredientsList(expectedList);

        when(foodIngredientsService.findByFoodId(id)).thenReturn(expectedList);
        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The ingredient with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findByFoodId(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The ingredient with this id doesn't exist in the database!");

        verify(foodIngredientsService).findByFoodId(id);
        verify(repository).findById(id);
    }
}