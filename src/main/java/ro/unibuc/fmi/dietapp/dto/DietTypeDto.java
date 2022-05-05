package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietTypeDto {
    private Long id;

    private String name;
}
