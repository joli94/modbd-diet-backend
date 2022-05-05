package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private Long id;

    private String name;
    private String code;
}
