package ro.unibuc.fmi.dietapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.fmi.dietapp.exception.BadRequestException;
import ro.unibuc.fmi.dietapp.model.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private CityService cityService;

    @InjectMocks
    private RegistrationService service;

    private Registration expected;

    @BeforeEach
    void setUp() {
        expected = Registration.builder()
                .username("email@unibuc.ro")
                .first_name("Adrian")
                .last_name("Apostol")
                .birth_date(LocalDate.now())
                .gender("M")
                .city(1L)
                .password("ParolaDeTest")
                .build();
    }

    @Test
    @DisplayName("Create a new account - happy flow")
    public void test_createAccount_happyFlow() {
        User user = User.builder()
                .id(1L)
                .username("email@unibuc.ro")
                .first_name("Adrian")
                .last_name("Apostol")
                .target("slabire")
                .birth_date(LocalDate.now())
                .isAdmin(false)
                .gender("M")
                .city(City.builder().id(1L).name("Bucuresti").country(Country.builder().id(1L).name("Romania").build()).build())
                .account(Account.builder().id(1L).build())
                .build();

        when(userService.existsByUsername(expected.getUsername())).thenReturn(false);

        when(userService.create(any())).thenReturn(user);

        service.create(expected);

        verify(userService).existsByUsername(expected.getUsername());
        verify(userService).create(any());
    }

    @Test
    @DisplayName("Create a new account - username is already taken")
    public void test_createAccount_throwsBadRequestException_whenUsernameIsAlreadyTaken() {
        when(userService.existsByUsername(expected.getUsername())).thenReturn(true);

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () ->
                service.create(expected)
        );

        assertThat(exception.getMessage()).isEqualTo("This username is already taken");

        verify(userService, times(0)).create(any());
        verify(userService).existsByUsername(expected.getUsername());
    }
}