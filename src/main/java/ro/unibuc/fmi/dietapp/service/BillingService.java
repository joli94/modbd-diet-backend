package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.Billing;
import ro.unibuc.fmi.dietapp.repository.BillingRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillingService {
    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
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

    public Billing create(Billing billing) {
        billing.getPayment().setDate(LocalDate.now());
        return billingRepository.save(billing);
    }
}
