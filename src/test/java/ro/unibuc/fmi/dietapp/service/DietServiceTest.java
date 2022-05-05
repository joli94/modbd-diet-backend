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
import ro.unibuc.fmi.dietapp.model.Diet;
import ro.unibuc.fmi.dietapp.model.DietGoal;
import ro.unibuc.fmi.dietapp.model.DietType;
import ro.unibuc.fmi.dietapp.repository.DietRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DietServiceTest {
    @Mock
    private DietRepository repository;

    @InjectMocks
    private DietService service;

    private Diet expected;

    @BeforeEach
    void setUp() {
        expected = Diet.builder()
                .id(1L)
                .name("Dieta fulger")
                .maximumCalories(1500L)
                .price(250L)
                .dietGoal(DietGoal.builder().id(1L).name("slabire").build())
                .dietType(DietType.builder().id(1L).name("proteic").build())
                .build();
    }

    @Test
    @DisplayName("Find all diets - happy flow")
    public void test_findAllDiets_happyFlow() {
        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<Diet> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getMaximumCalories(), result.stream().findFirst().get().getMaximumCalories());
        assertEquals(expected.getPrice(), result.stream().findFirst().get().getPrice());
        assertEquals(expected.getDietGoal(), result.stream().findFirst().get().getDietGoal());
        assertEquals(expected.getDietType(), result.stream().findFirst().get().getDietType());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find all diets by goal id - happy flow")
    public void test_findAllDietsByGoalId_happyFlow() {
        Long id = expected.getDietGoal().getId();

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByDietGoalId(id)).thenReturn(expectedList);

        List<Diet> result = service.findByGoalId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getMaximumCalories(), result.stream().findFirst().get().getMaximumCalories());
        assertEquals(expected.getPrice(), result.stream().findFirst().get().getPrice());
        assertEquals(expected.getDietGoal(), result.stream().findFirst().get().getDietGoal());
        assertEquals(expected.getDietType(), result.stream().findFirst().get().getDietType());

        verify(repository).findByDietGoalId(id);
    }

    @Test
    @DisplayName("Find all diets by type id - happy flow")
    public void test_findAllDietsByTypeId_happyFlow() {
        Long id = expected.getDietType().getId();

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByDietTypeId(id)).thenReturn(expectedList);

        List<Diet> result = service.findByTypeId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getMaximumCalories(), result.stream().findFirst().get().getMaximumCalories());
        assertEquals(expected.getPrice(), result.stream().findFirst().get().getPrice());
        assertEquals(expected.getDietGoal(), result.stream().findFirst().get().getDietGoal());
        assertEquals(expected.getDietType(), result.stream().findFirst().get().getDietType());

        verify(repository).findByDietTypeId(id);
    }

    @Test
    @DisplayName("Find a diet by id - happy flow")
    public void test_findDietById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Diet result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getMaximumCalories(), result.getMaximumCalories());
        assertEquals(expected.getPrice(), result.getPrice());
        assertEquals(expected.getDietGoal(), result.getDietGoal());
        assertEquals(expected.getDietType(), result.getDietType());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a diet by id - id doesn't exist in the database")
    public void test_findDietById_throwsEntityNotFoundException_whenDietNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The diet with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The diet with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Update a diet - happy flow")
    public void test_updateDiet_happyFlow() {
        Diet diet = expected;
        Long id = expected.getId();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(expected)).thenReturn(expected);

        Diet result = service.update(diet);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getMaximumCalories(), result.getMaximumCalories());
        assertEquals(expected.getPrice(), result.getPrice());
        assertEquals(expected.getDietGoal(), result.getDietGoal());
        assertEquals(expected.getDietType(), result.getDietType());


        verify(repository).save(diet);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Update a diet - diet doesn't exist in the database")
    public void test_updateDiet_throwsEntityNotFoundException_whenDietNotFound() {
        Long id = new Random().nextLong();
        expected.setId(id);

        when(repository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.update(expected)
        );

        assertThat(exception.getMessage()).isEqualTo(String.format("There is no diet with id=%s in the database!", id));

        verify(repository, times(0)).save(expected);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Delete a diet - happy flow")
    public void test_deleteDiet_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));
        doNothing().when(repository).delete(expected);

        service.delete(id);

        verify(repository).findById(id);
        verify(repository).delete(expected);
    }
}