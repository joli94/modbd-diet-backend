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
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOOD_CATEGORY_ID")
    private Long id;

    @Column(name = "FOOD_CATEGORY_NAME")
    private String name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.PERSIST)
    private List<Food> foodList;
}
