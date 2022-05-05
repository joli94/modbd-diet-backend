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
import ro.unibuc.fmi.dietapp.model.FoodCategory;
import ro.unibuc.fmi.dietapp.repository.FoodCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodCategoryServiceTest {
    @Mock
    private FoodCategoryRepository repository;

    @InjectMocks
    private FoodCategoryService service;

    private FoodCategory expected;

    @BeforeEach
    void setUp() {
        expected = FoodCategory.builder()
                .id(1L)
                .name("breakfast")
                .build();
    }

    @Test
    @DisplayName("Find all food categories - happy flow")
    public void test_findAllFoodCategories_happyFlow() {
        List<FoodCategory> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<FoodCategory> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find a food category by id - happy flow")
    public void test_findFoodCategoryById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        FoodCategory result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a food category by id - id doesn't exist in the database")
    public void test_findFoodCategoryById_throwsEntityNotFoundException_whenFoodCategoryNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The food category with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The food category with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }
}