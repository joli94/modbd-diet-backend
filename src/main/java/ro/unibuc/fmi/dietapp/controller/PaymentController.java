package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.PaymentDto;
import ro.unibuc.fmi.dietapp.mapper.PaymentMapper;
import ro.unibuc.fmi.dietapp.model.Payment;
import ro.unibuc.fmi.dietapp.service.PaymentService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;
    private final PaymentMapper mapper;

    public PaymentController(PaymentService service, PaymentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> findAll() {
        List<Payment> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable Long id) {
        Payment response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto request) {
        Payment response = service.create(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.CREATED);
    }
}
