package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BILLING_ID")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Diet diet;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Payment payment;
}
