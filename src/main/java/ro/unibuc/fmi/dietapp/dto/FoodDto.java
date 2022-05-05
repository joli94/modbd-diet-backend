package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDto {
    private Long id;

    private String name;

    private Long calories;

    private Long foodCategoryId;
}
