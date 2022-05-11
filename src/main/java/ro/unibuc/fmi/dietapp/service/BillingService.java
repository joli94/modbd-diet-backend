package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.Billing;
import ro.unibuc.fmi.dietapp.model.Payment;
import ro.unibuc.fmi.dietapp.repository.BillingRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillingService {
    private final BillingRepository billingRepository;
    private final PaymentService paymentService;

    public BillingService(BillingRepository billingRepository, PaymentService paymentService) {
        this.billingRepository = billingRepository;
        this.paymentService = paymentService;
    }

    public List<Billing> findAll() {
        return billingRepository.findAll();
    }

    public List<Billing> findByUser(Long id) {
        return billingRepository.findByUserId(id);
    }

    public Billing findById(Long id) {
        return billingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The billing with this id doesn't exist in the database!")
        );
    }

    public void create(Billing billing) {
        Payment payment = Payment.builder()
                .date(LocalDate.now())
                .amount(120L)
                .build();

        Payment response = paymentService.create(payment);
        billingRepository.create(billing.getDiet().getId(), response.getId(), billing.getUser().getId());
    }
}
