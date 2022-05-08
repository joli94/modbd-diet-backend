package ro.unibuc.fmi.dietapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String first_name;
    private String last_name;

    private String target;

    private String gender;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;
    private String cnp;
    private String cardNumber;

    private String isAdmin;

    private Long cityId;
}
