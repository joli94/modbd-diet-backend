package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByCityId(Long id) {
        return userRepository.findByCityId(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The user with this id doesn't exist in the database!")
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("The user with this username doesn't exist in the database!")
        );
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException(String.format("There is no user with id=%s in the database!", user.getId().toString()));
        }
    }

    public void changeAdmin(Long id) {
        User toBeChanged = findById(id);

        toBeChanged.setIsAdmin(!toBeChanged.getIsAdmin());

        userRepository.save(toBeChanged);
    }
}
