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
import ro.unibuc.fmi.dietapp.model.DietGoal;
import ro.unibuc.fmi.dietapp.repository.DietGoalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DietGoalServiceTest {
    @Mock
    private DietGoalRepository repository;

    @InjectMocks
    private DietGoalService service;

    private DietGoal expected;

    @BeforeEach
    void setUp() {
        expected = DietGoal.builder()
                .id(1L)
                .name("slabire")
                .build();
    }

    @Test
    @DisplayName("Find all diet goals - happy flow")
    public void test_findAllDietGoals_happyFlow() {
        Long id = expected.getId();

        List<DietGoal> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<DietGoal> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find a diet goal by id - happy flow")
    public void test_findDietGoalById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        DietGoal result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a diet goal by id - id doesn't exist in the database")
    public void test_findDietGoalById_throwsEntityNotFoundException_whenDietGoalNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The goal diet with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The goal diet with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }
}