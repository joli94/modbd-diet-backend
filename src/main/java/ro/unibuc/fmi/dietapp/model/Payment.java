package ro.unibuc.fmi.dietapp.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @NotFound(action = NotFoundAction.IGNORE)
    private Billing billing;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;
}
