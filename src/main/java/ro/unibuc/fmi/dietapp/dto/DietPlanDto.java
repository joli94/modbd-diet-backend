package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietPlanDto {
    private Long id;

    private DietDto dietDto;

    //private FoodDto foodDto;
}
