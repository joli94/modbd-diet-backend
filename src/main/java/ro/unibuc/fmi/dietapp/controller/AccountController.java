package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.AccountDto;
import ro.unibuc.fmi.dietapp.exception.BadRequestException;
import ro.unibuc.fmi.dietapp.mapper.AccountMapper;
import ro.unibuc.fmi.dietapp.model.Account;
import ro.unibuc.fmi.dietapp.service.AccountService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;
    private final AccountMapper mapper;

    public AccountController(AccountService service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable Long id) {
        Account response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto request) {
        if (!id.equals(request.getId())) {
            throw new BadRequestException("The path variable doesn't match the request body id!");
        }

        Account response = service.update(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }
}
