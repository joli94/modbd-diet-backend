package ro.unibuc.fmi.dietapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.dietapp.dto.UserDto;
import ro.unibuc.fmi.dietapp.exception.BadRequestException;
import ro.unibuc.fmi.dietapp.mapper.UserMapper;
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> response = service.findAll();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/ea")
    public ResponseEntity<List<UserDto>> findAllInEasternEurope() {
        List<User> response = service.findAllInEaEu();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }
    @GetMapping("/we")
    public ResponseEntity<List<UserDto>> findAllinWesternEurope() {
        List<User> response = service.findAllInWeEu();
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/country")
    public ResponseEntity<List<UserDto>> findByCountry(@RequestParam Long id) {
        List<User> response = service.findByCountryId(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/u/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        User response = service.findById(id);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String request) {
        User response = service.findByUsername(request);
        return new ResponseEntity<>(mapper.toDto(response), HttpStatus.OK);
    }

    @PutMapping("/u/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDto request) {
        if (!id.equals(request.getId())) {
            throw new BadRequestException("The path variable doesn't match the request body id!");
        }

        service.update(mapper.toEntity(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changeAdmin")
    public ResponseEntity<Void> changeAdmin(@RequestParam Long id, @RequestParam String role) {
        service.changeAdmin(id, role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
