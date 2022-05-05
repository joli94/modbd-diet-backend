package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietDto {
    private Long id;

    private String name;

    private long price;

    private long maximumCalories;

    private byte[] image;

    private Long dietGoalId;

    private Long dietTypeId;
}
