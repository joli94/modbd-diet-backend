package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.WeightDto;
import ro.unibuc.fmi.dietapp.mapper.WeightMapper;
import ro.unibuc.fmi.dietapp.model.Weight;
import ro.unibuc.fmi.dietapp.service.WeightService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/weights")
public class WeightController {
    private final WeightService service;
    private final WeightMapper mapper;

    public WeightController(WeightService service, WeightMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<WeightDto>> findAll() {
        List<Weight> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<WeightDto>> findByUserId(@RequestParam Long id) {
        List<Weight> response = service.findByUserId(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightDto> findById(@PathVariable Long id) {
        Weight response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody WeightDto request) {
        service.create(mapper.toEntity(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
