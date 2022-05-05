package ro.unibuc.fmi.dietapp.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;

    private LocalDate date;

    private Long amount;
}
