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
import ro.unibuc.fmi.dietapp.model.City;
import ro.unibuc.fmi.dietapp.model.Country;
import ro.unibuc.fmi.dietapp.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService service;

    private City expected;

    @BeforeEach
    void setUp() {
        expected = City.builder()
                .id(1L)
                .name("Bucharest")
                .country(Country.builder().id(1L).name("Romania").build())
                .build();
    }
    @Test
    @DisplayName("Find all cities - happy flow")
    public void test_findAllCities_happyFlow() {
        List<City> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<City> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCountry(), result.stream().findFirst().get().getCountry());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find all cities by country id - happy flow")
    public void test_findAllCitiesByCountryId_happyFlow() {
        Long id = expected.getCountry().getId();

        List<City> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByCountryId(id)).thenReturn(expectedList);

        List<City> result = service.findByCountryId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getName(), result.stream().findFirst().get().getName());
        assertEquals(expected.getCountry(), result.stream().findFirst().get().getCountry());

        verify(repository).findByCountryId(id);
    }

    @Test
    @DisplayName("Find a city  by id - happy flow")
    public void test_findCityById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        City result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCountry(), result.getCountry());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a city by id - id doesn't exist in the database")
    public void test_findCityById_throwsEntityNotFoundException_whenCityNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The country with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The country with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }
}