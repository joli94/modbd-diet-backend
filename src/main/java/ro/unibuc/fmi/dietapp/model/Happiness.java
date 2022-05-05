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
public class Happiness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HAPPINESS_ID")
    private Long id;

    @Column(name = "HAPPINESS_DATE")
    private LocalDateTime date;

    @Column(name = "HAPPINESS_NUMBER")
    private Long value;

    @ManyToOne
    private User user;
}
