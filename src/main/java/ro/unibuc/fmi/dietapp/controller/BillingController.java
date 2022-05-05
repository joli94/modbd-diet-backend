package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.BillingDto;
import ro.unibuc.fmi.dietapp.mapper.BillingMapper;
import ro.unibuc.fmi.dietapp.model.Billing;
import ro.unibuc.fmi.dietapp.service.BillingService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/billings")
public class BillingController {
    private final BillingService service;
    private final BillingMapper mapper;

    public BillingController(BillingService service, BillingMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BillingDto>> findAll() {
        return new ResponseEntity<>(mapper.toDto(service.findAll()), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<BillingDto>> findByUser(@RequestParam Long id) {
        List<Billing> response = service.findByUser(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDto> findById(@PathVariable Long id) {
        Billing response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BillingDto> create(@RequestBody BillingDto request) {
        Billing response = service.create(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.CREATED);
    }
}
