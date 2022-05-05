package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID")
    private Long id;

    @Column(name = "PAYMENT_DATE")
    private LocalDate date;

    @Column(name = "PAYMENT_TOTAL")
    private Long amount;

    @OneToOne
    private Billing billing;
}
