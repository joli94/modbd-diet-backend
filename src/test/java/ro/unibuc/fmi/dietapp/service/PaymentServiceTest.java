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
import ro.unibuc.fmi.dietapp.model.Payment;
import ro.unibuc.fmi.dietapp.repository.PaymentRepository;

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
public class PaymentServiceTest {
    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentService service;

    private Payment expected;

    @BeforeEach
    void setUp() {
        expected = Payment.builder()
                .id(1L)
                .date(LocalDate.now())
                .amount(500L)
                .billing(Billing.builder().id(1L).build())
                .build();
    }

    @Test
    @DisplayName("Find all payments - happy flow")
    public void test_findAllPayments_happyFlow() {
        List<Payment> expectedList = new ArrayList<>();
        expectedList.add(expected);

        when(repository.findAll()).thenReturn(expectedList);

        List<Payment> result = service.findAll();

        assertEquals(expectedList.size(), result.size());
        assertEquals(expected.getId(), result.stream().findFirst().get().getId());
        assertEquals(expected.getDate(), result.stream().findFirst().get().getDate());
        assertEquals(expected.getAmount(), result.stream().findFirst().get().getAmount());
        assertEquals(expected.getBilling(), result.stream().findFirst().get().getBilling());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find a payment by id - happy flow")
    public void test_findPaymentById_happyFlow() {
        Long id = expected.getId();

        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Payment result = service.findById(id);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount());
        assertEquals(expected.getBilling(), result.getBilling());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Find a payment by id - id doesn't exist in the database")
    public void test_findPaymentById_throwsEntityNotFoundException_whenPaymentNotFound() {
        Long id = new Random().nextLong();

        when(repository.findById(id)).thenThrow(new EntityNotFoundException("The payment with this id doesn't exist in the database!"));

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("The payment with this id doesn't exist in the database!");

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Create a new payment - happy flow")
    public void test_createPayment_happyFlow() {
        Payment payment = Payment.builder()
                .date(LocalDate.now())
                .amount(500L)
                .billing(Billing.builder().id(1L).build())
                .build();

        when(repository.save(payment)).thenReturn(expected);

        Payment result = service.create(payment);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount());
        assertEquals(expected.getBilling(), result.getBilling());

        verify(repository).save(payment);
    }
}