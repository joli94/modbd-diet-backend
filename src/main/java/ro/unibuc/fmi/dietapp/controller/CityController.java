package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.CityDto;
import ro.unibuc.fmi.dietapp.mapper.CityMapper;
import ro.unibuc.fmi.dietapp.model.City;
import ro.unibuc.fmi.dietapp.service.CityService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService service;
    private final CityMapper mapper;

    public CityController(CityService service, CityMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll() {
        List<City> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/country")
    public ResponseEntity<List<CityDto>> findByCountryId(@RequestParam Long id) {
        List<City> response = service.findByCountryId(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> findById(@PathVariable Long id) {
        City response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }
}
