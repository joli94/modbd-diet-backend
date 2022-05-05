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
import ro.unibuc.fmi.dietapp.model.Account;
import ro.unibuc.fmi.dietapp.model.City;
import ro.unibuc.fmi.dietapp.model.Country;
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User expected;

    @BeforeEach
    void setUp() {
        expected = User.builder()
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
    }

    @Test
    @DisplayName("Find all users - happy flow")
    public void test_findAllUsers_happyFlow() {
        List<User> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<User> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getUsername(), result.stream().findFirst().get().getUsername());
        assertEquals(expected.getFirst_name(), result.stream().findFirst().get().getFirst_name());
        assertEquals(expected.getLast_name(), result.stream().findFirst().get().getLast_name());
        assertEquals(expected.getTarget(), result.stream().findFirst().get().getTarget());
        assertEquals(expected.getBirth_date(), result.stream().findFirst().get().getBirth_date());
        assertEquals(expected.getIsAdmin(), result.stream().findFirst().get().getIsAdmin());
        assertEquals(expected.getGender(), result.stream().findFirst().get().getGender());
        assertEquals(expected.getCity(), result.stream().findFirst().get().getCity());
        assertEquals(expected.getAccount(), result.stream().findFirst().get().getAccount());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find all users by city id - happy flow")
    public void test_findAllUsersByCityId_happyFlow() {
        Long id = expected.getCity().getId();

        List<User> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findByCityId(id)).thenReturn(expectedList);

        List<User> result = service.findByCityId(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getUsername(), result.stream().findFirst().get().getUsername());
        assertEquals(expected.getFirst_name(), result.stream().findFirst().get().getFirst_name());
        assertEquals(expected.getLast_name(), result.stream().findFirst().get().getLast_name());
        assertEquals(expected.getTarget(), result.stream().findFirst().get().getTarget());
        assertEquals(expected.getBirth_date(), result.stream().findFirst().get().getBirth_date());
        assertEquals(expected.getIsAdmin(), result.stream().findFirst().get().getIsAdmin());
        assertEquals(expected.getGender(), result.stream().findFirst().get().getGender());
        assertEquals(expected.getCity(), result.stream().findFirst().get().getCity());
        assertEquals(expected.getAccount(), result.stream().findFirst().get().getAccount());

        verify(repository).findByCityId(id);
    }

    @Test
    @DisplayName("Checks if an user exists by username - user exists")
    public void test_existUserByUsername_userExists() {
        String username = expected.getUsername();

        when(repository.existsByUsername(username)).thenReturn(true);

        Boolean result = service.existsByUsername(username);

        assertEquals(true, result);

        verify(repository).existsByUsername(username);
    }

    @Test
    @DisplayName("Checks if an user exists by username - user doesn't exist")
    public void test_existUserByUsername_userDoesntExist() {
        String username = expected.getUsername();

        when(repository.existsByUsername(username)).thenReturn(false);

        Boolean result = service.existsByUsername(username);

        assertEquals(false, result);

        verify(repository).existsByUsername(username);
    }

    @Test
    @DisplayName("Find an user by id - happy flow")
    public void test_findUserById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        User result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getUsername(), result.getUsername());
        assertEquals(expected.getFirst_name(), result.getFirst_name());
        assertEquals(expected.getLast_name(), result.getLast_name());
        assertEquals(expected.getTarget(), result.getTarget());
        assertEquals(expected.getBirth_date(), result.getBirth_date());
        assertEquals(expected.getIsAdmin(), result.getIsAdmin());
        assertEquals(expected.getGender(), result.getGender());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getAccount(), result.getAccount());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find an user by id - id doesn't exist in the database")
    public void test_findUserById_throwsEntityNotFoundException_whenUserNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The user with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The user with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find an user by username - happy flow")
    public void test_findUserByUsername_happyFlow() {
        String username = expected.getUsername();

        when(repository.findByUsername(username)).thenReturn(Optional.of(expected));

        User result = service.findByUsername(username);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getUsername(), result.getUsername());
        assertEquals(expected.getFirst_name(), result.getFirst_name());
        assertEquals(expected.getLast_name(), result.getLast_name());
        assertEquals(expected.getTarget(), result.getTarget());
        assertEquals(expected.getBirth_date(), result.getBirth_date());
        assertEquals(expected.getIsAdmin(), result.getIsAdmin());
        assertEquals(expected.getGender(), result.getGender());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getAccount(), result.getAccount());

        verify(repository).findByUsername(username);
    }

    @Test
    @DisplayName("Find an user by username - username doesn't exist in the database")
    public void test_findUserByUsername_throwsEntityNotFoundException_whenUserNotFound() {
        String username = "test";

        when(repository.findByUsername(username)).thenThrow(new EntityNotFoundException("The user with this username doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findByUsername(username)
        );

        assertThat(exception.getMessage()).isEqualTo("The user with this username doesn't exist in the database!");

        verify(repository).findByUsername(username);
    }

    @Test
    @DisplayName("Create a new user - happy flow")
    public void test_createWeight_happyFlow() {
        User user = User.builder()
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

        when(repository.save(user)).thenReturn(expected);

        User result = service.create(user);

        assertNotNull(result.getId());
        assertEquals(expected.getUsername(), result.getUsername());
        assertEquals(expected.getFirst_name(), result.getFirst_name());
        assertEquals(expected.getLast_name(), result.getLast_name());
        assertEquals(expected.getTarget(), result.getTarget());
        assertEquals(expected.getBirth_date(), result.getBirth_date());
        assertEquals(expected.getIsAdmin(), result.getIsAdmin());
        assertEquals(expected.getGender(), result.getGender());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getAccount(), result.getAccount());

        verify(repository).save(user);
    }

    @Test
    @DisplayName("Update an user - happy flow")
    public void test_updateUser_happyFlow() {
        User user = expected;
        Long id = expected.getId();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(user)).thenReturn(expected);

        User result = service.update(user);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getUsername(), result.getUsername());
        assertEquals(expected.getFirst_name(), result.getFirst_name());
        assertEquals(expected.getLast_name(), result.getLast_name());
        assertEquals(expected.getTarget(), result.getTarget());
        assertEquals(expected.getBirth_date(), result.getBirth_date());
        assertEquals(expected.getIsAdmin(), result.getIsAdmin());
        assertEquals(expected.getGender(), result.getGender());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getAccount(), result.getAccount());

        verify(repository).save(user);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Update an user - user doesn't exist in the database")
    public void test_updateUser_throwsEntityNotFoundException_whenUserNotFound() {
        Long id = new Random().nextLong();
        expected.setId(id);

        when(repository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.update(expected)
        );

        assertThat(exception.getMessage()).isEqualTo(String.format("There is no user with id=%s in the database!", id));

        verify(repository, times(0)).save(expected);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Change admin rights for an user - user becomes admin")
    public void test_changeAdmin_userBecomesAdmin() {
        Long id = expected.getId();
        expected.setIsAdmin(true);

        User user = expected;
        user.setIsAdmin(false);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(expected);

        service.changeAdmin(id);

        verify(repository).save(user);
        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Change admin rights for an user - user loses admin")
    public void test_changeAdmin_userLosesAdmin() {
        Long id = expected.getId();
        expected.setIsAdmin(false);

        User user = expected;
        user.setIsAdmin(true);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(expected);

        service.changeAdmin(id);

        verify(repository).save(user);
        verify(repository).findById(id);
    }
}