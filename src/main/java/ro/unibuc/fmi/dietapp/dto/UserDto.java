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

    private String username;
    private String first_name;
    private String last_name;

    private String target;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;

    private String gender;

    private Long cityId;

    private Boolean isAdmin;
}
