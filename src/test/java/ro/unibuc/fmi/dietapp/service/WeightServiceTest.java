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
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.model.Weight;
import ro.unibuc.fmi.dietapp.repository.WeightRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeightServiceTest {
    @Mock
    private WeightRepository repository;

    @InjectMocks
    private WeightService service;

    private Weight expected;

    @BeforeEach
    void setUp() {
        expected = Weight.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .value(80D)
                .user(User.builder().id(1L).build())
                .build();
    }

    @Test
    @DisplayName("Find all weights - happy flow")
    public void test_findAllWeights_happyFlow() {
        List<Weight> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<Weight> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getDate(), result.stream().findFirst().get().getDate());
        assertEquals(expected.getValue(), result.stream().findFirst().get().getValue());
        assertEquals(expected.getUser(), result.stream().findFirst().get().getUser());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find all weights by user id - happy flow")
    public void test_findAllWeightsByUserId_happyFlow() {
        Long id = expected.getUser().getId();

        List<Weight> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByUserId(id)).thenReturn(expectedList);

        List<Weight> result = service.findByUserId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getDate(), result.stream().findFirst().get().getDate());
        assertEquals(expected.getValue(), result.stream().findFirst().get().getValue());
        assertEquals(expected.getUser(), result.stream().findFirst().get().getUser());

        verify(repository).findByUserId(id);
    }

    @Test
    @DisplayName("Find a weight by id - happy flow")
    public void test_findWeightById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Weight result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getValue(), result.getValue());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a weight by id - id doesn't exist in the database")
    public void test_findWeightById_throwsEntityNotFoundException_whenWeightNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The weight measurement with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The weight measurement with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Create a new weight - happy flow")
    public void test_createWeight_happyFlow() {
        Weight weight = Weight.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .value(80D)
                .user(User.builder().id(1L).build())
                .build();
        expected.setDate(weight.getDate());

        when(repository.save(weight)).thenReturn(expected);

        Weight result = service.create(weight);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getValue(), result.getValue());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).save(weight);
    }

    @Test
    @DisplayName("Update a weight - happy flow")
    public void test_updateWeight_happyFlow() {
        Weight weight = expected;
        Long id = expected.getId();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(weight)).thenReturn(expected);

        Weight result = service.update(weight);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getValue(), result.getValue());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).save(weight);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Update a weight - weight doesn't exist in the database")
    public void test_updateWeight_throwsEntityNotFoundException_whenWeightNotFound() {
        Long id = new Random().nextLong();
        expected.setId(id);

        when(repository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.update(expected)
        );

        assertThat(exception.getMessage()).isEqualTo(String.format("There is no weight measurement with id=%s in the database!", id));

        verify(repository, times(0)).save(expected);
        verify(repository).existsById(id);
    }
}