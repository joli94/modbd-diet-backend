package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {
    private String username;
    private String first_name;
    private String last_name;


    private LocalDate birth_date;

    private String gender;

    private Long city;

    private String password;
}
