package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.CountryDto;
import ro.unibuc.fmi.dietapp.mapper.CountryMapper;
import ro.unibuc.fmi.dietapp.model.Country;
import ro.unibuc.fmi.dietapp.service.CountryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService service;
    private final CountryMapper mapper;

    public CountryController(CountryService service, CountryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAll() {
        List<Country> result = service.findAll();
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        Country result = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.OK);
    }

}
