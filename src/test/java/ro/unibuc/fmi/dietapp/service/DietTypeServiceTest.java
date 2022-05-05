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
import ro.unibuc.fmi.dietapp.model.DietType;
import ro.unibuc.fmi.dietapp.repository.DietTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DietTypeServiceTest {
    @Mock
    private DietTypeRepository repository;

    @InjectMocks
    private DietTypeService service;

    private DietType expected;

    @BeforeEach
    void setUp() {
        expected = DietType.builder()
                .id(1L)
                .name("proteica")
                .build();
    }

    @Test
    @DisplayName("Find all diet types - happy flow")
    public void test_findAllDietTypes_happyFlow() {
        Long id = expected.getId();

        List<DietType> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<DietType> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find a diet type by id - happy flow")
    public void test_findDietTypeById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        DietType result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a diet type by id - id doesn't exist in the database")
    public void test_findDietTypeById_throwsEntityNotFoundException_whenDietTypeNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The type diet with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The type diet with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }
}