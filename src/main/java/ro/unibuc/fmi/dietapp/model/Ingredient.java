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
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INGREDIENT_ID")
    private Long id;

    @Column(name = "INGREDIENT_NAME")
    private String name;

    @Column(name = "INGREDIENT_CALORIES")
    private Long calories;

    @OneToMany(mappedBy = "ingredient", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<FoodIngredients> foodIngredientsList;
}
