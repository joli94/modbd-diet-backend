package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DIET_PLAN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIET_PLAN_ID")
    private Long id;

    @ManyToOne
    private Diet diet;

    @ManyToOne
    private Food food;
}
