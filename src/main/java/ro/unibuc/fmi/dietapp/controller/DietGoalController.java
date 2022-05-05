package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.DietGoalDto;
import ro.unibuc.fmi.dietapp.mapper.DietGoalMapper;
import ro.unibuc.fmi.dietapp.model.DietGoal;
import ro.unibuc.fmi.dietapp.service.DietGoalService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dietGoals")
public class DietGoalController {
    private final DietGoalService service;
    private final DietGoalMapper mapper;

    public DietGoalController(DietGoalService service, DietGoalMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<DietGoalDto>> findAll() {
        List<DietGoal> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietGoalDto> findById(@PathVariable Long id) {
        DietGoal response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }
}
