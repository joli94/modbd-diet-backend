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
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.repository.AccountRepository;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    private Account expected;

    @BeforeEach
    void setUp() {
        expected = Account.builder()
                .id(1L)
                .active("T")
                .password("ParolaDeTest")
                .role("USER")
                .user(User.builder().id(1L).build())
                .build();
    }

    @Test
    @DisplayName("Find an account by id - happy flow")
    public void test_finAccountById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Account result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getActive(), result.getActive());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getRole(), result.getRole());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find an account by id - id doesn't exist in the database")
    public void test_findAccountById_throwsEntityNotFoundException_whenAccountNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The account with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The account with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Create a new account - happy flow")
    public void test_createWeight_happyFlow() {
        Account account = Account.builder()
                .id(1L)
                .active("T")
                .password("ParolaDeTest")
                .role("USER")
                .user(User.builder().id(1L).build())
                .build();

        when(repository.save(account)).thenReturn(expected);

        Account result = service.create(account);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getActive(), result.getActive());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getRole(), result.getRole());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).save(account);
    }

    @Test
    @DisplayName("Update an account - happy flow")
    public void test_updateAccount_happyFlow() {
        Account account = expected;
        Long id = expected.getId();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(account)).thenReturn(expected);

        Account result = service.update(account);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getActive(), result.getActive());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getRole(), result.getRole());
        assertEquals(expected.getUser(), result.getUser());

        verify(repository).save(account);
        verify(repository).existsById(id);
    }

    @Test
    @DisplayName("Update an account - account doesn't exist in the database")
    public void test_updateAccount_throwsEntityNotFoundException_whenAccountNotFound() {
        Long id = new Random().nextLong();
        expected.setId(id);

        when(repository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.update(expected)
        );

        assertThat(exception.getMessage()).isEqualTo(String.format("There is no account with id=%s in the database!", id));

        verify(repository, times(0)).save(expected);
        verify(repository).existsById(id);
    }
}