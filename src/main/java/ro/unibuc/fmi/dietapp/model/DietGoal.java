package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIET_GOAL_ID")
    private Long id;

    @Column(name = "DIET_GOAL_NAME")
    private String name;

    @OneToMany(mappedBy = "dietGoal", cascade = CascadeType.PERSIST)
    private List<Diet> dietList;
}
