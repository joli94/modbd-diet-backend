package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Optimum_caloriesDto {
    private Long id;

    private Long age;

    private String gender;
    private Long calories;

    private List<UserDto> userDtoList;
}
