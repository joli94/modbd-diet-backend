package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration {
    private String username;
    private String first_name;
    private String last_name;

    private LocalDate birth_date;

    private String gender;

    private Long city;
    private String country;

    private String password;
}
