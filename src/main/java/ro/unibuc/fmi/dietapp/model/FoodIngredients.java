package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FOOD_INGREDIENTS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOOD_INGREDIENTS_ID")
    private Long id;

    @ManyToOne
    private Food food;

    @ManyToOne
    private Ingredient ingredient;
}
