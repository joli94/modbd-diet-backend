package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.HappinessDto;
import ro.unibuc.fmi.dietapp.exception.BadRequestException;
import ro.unibuc.fmi.dietapp.mapper.HappinessMapper;
import ro.unibuc.fmi.dietapp.model.Happiness;
import ro.unibuc.fmi.dietapp.service.HappinessService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/happiness")
public class HappinessController {
    private final HappinessService service;
    private final HappinessMapper mapper;

    public HappinessController(HappinessService service, HappinessMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<HappinessDto>> findAll() {
        List<Happiness> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<HappinessDto>> findByUserId(@RequestParam Long id) {
        List<Happiness> response = service.findByUserId(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HappinessDto> findById(@PathVariable Long id) {
        Happiness response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HappinessDto> create(@RequestBody HappinessDto request) {
        Happiness response = service.create(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HappinessDto> update(@PathVariable Long id, @RequestBody HappinessDto request) {
        if (!id.equals(request.getId())) {
            throw new BadRequestException("The path variable doesn't match the request body id!");
        }

        Happiness response = service.update(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }
}
