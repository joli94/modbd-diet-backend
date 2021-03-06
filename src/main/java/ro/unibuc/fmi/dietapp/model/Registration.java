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

    private String target;
    private String gender;

    private Long country;

    private String cnp;
    private String cardNumber;
}
