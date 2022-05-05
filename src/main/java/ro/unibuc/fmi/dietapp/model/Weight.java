package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WEIGHT_ID")
    private Long id;

    @Column(name = "WEIGHT_DATE")
    private LocalDateTime date;

    @Column(name = "WEIGHT_NUMBER")
    private Double value;

    @ManyToOne
    private User user;
}
