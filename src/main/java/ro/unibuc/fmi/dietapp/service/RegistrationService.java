package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.BadRequestException;
import ro.unibuc.fmi.dietapp.model.Registration;
import ro.unibuc.fmi.dietapp.model.User;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrationService {
    private final UserService userService;
    private final CityService cityService;

    public RegistrationService(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;

    }

    public void create(Registration registration) {
        if (!userService.existsByUsername(registration.getUsername())) {

            User registeredUser = User.builder()
                    .username(registration.getUsername())
                    .first_name(registration.getFirst_name())
                    .last_name(registration.getLast_name())
                    .birth_date(registration.getBirth_date())
                    .gender(registration.getGender())
                    .city(cityService.findById(registration.getCity()))
                    .isAdmin(false)
                    .build();

            User resultedUser = userService.create(registeredUser);

        } else {
            throw new BadRequestException("This username is already taken");
        }
    }
}
