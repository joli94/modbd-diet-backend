package ro.unibuc.fmi.dietapp.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.User;
import ro.unibuc.fmi.dietapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private Session session;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllInEaEu() {
        return userRepository.findAllInEaEu();
    }

    public List<User> findAllInWeEu() {
        return userRepository.findAllInWeEu();
    }

    public List<User> findByCountryId(Long id) {
        return userRepository.findByCountryId(id);
    }

    /*public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }*/

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

    public void create(User user) {
        userRepository.create(user.getFirst_name(), user.getLast_name(), user.getGender(), user.getTarget(), user.getUsername(), user.getCountry().getId(), user.getBirth_date(), user.getCnp(), user.getCardNumber(), user.getIsAdmin());
    }

    public void update(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
             userRepository.update(user.getId(), user.getFirst_name(), user.getLast_name(), user.getGender(), user.getTarget(), user.getCnp(), user.getCardNumber(), user.getIsAdmin());
        } else {
            throw new EntityNotFoundException(String.format("There is no user with id=%s in the database!", user.getId().toString()));
        }
    }

    public void changeAdmin(Long id, String role) {
        User toBeChanged = findById(id);

        toBeChanged.setIsAdmin(role);

        userRepository.save(toBeChanged);
    }
}
