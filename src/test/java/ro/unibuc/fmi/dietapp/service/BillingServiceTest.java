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
import ro.unibuc.fmi.dietapp.model.Billing;
import ro.unibuc.fmi.dietapp.model.Diet;
import ro.unibuc.fmi.dietapp.model.Payment;
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.repository.BillingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {
    @Mock
    private BillingRepository repository;

    @InjectMocks
    private BillingService service;

    private Billing expected;

    @BeforeEach
    void setUp() {
        expected = Billing.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .diet(Diet.builder().id(1L).build())
                .payment(Payment.builder().id(1L).date(LocalDate.now()).build())
                .build();
    }

    @Test
    @DisplayName("Find all billings - happy flow")
    public void test_findAllBillings_happyFlow() {
        List<Billing> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<Billing> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getUser(), result.stream().findFirst().get().getUser());
        assertEquals(expected.getDiet(), result.stream().findFirst().get().getDiet());
        assertEquals(expected.getPayment(), result.stream().findFirst().get().getPayment());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find all billings by user id - happy flow")
    public void test_findAllBillingsByUserId_happyFlow() {
        List<Billing> expectedList = new ArrayList<>();
        expectedList.add(expected);

        Long id = expected.getUser().getId();

        when(repository.findByUserId(id)).thenReturn(expectedList);

        List<Billing> result = service.findByUser(id);

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getUser(), result.stream().findFirst().get().getUser());
        assertEquals(expected.getDiet(), result.stream().findFirst().get().getDiet());
        assertEquals(expected.getPayment(), result.stream().findFirst().get().getPayment());

        verify(repository).findByUserId(id);
    }

    @Test
    @DisplayName("Find a billing by id - happy flow")
    public void test_findBillingById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Billing result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getUser(), result.getUser());
        assertEquals(expected.getDiet(), result.getDiet());
        assertEquals(expected.getPayment(), result.getPayment());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a billing by id - id doesn't exist in the database")
    public void test_findBillingById_throwsEntityNotFoundException_whenBillingNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The billing with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The billing with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Create a new billing - happy flow")
    public void test_createBilling_happyFlow() {
        Billing billing = Billing.builder()
                .user(User.builder().id(1L).build())
                .diet(Diet.builder().id(1L).build())
                .payment(Payment.builder().id(1L).build())
                .build();

        when(repository.save(billing)).thenReturn(expected);

        Billing result = service.create(billing);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getUser(), result.getUser());
        assertEquals(expected.getDiet(), result.getDiet());
        assertEquals(expected.getPayment(), result.getPayment());

        verify(repository).save(billing);
    }
}