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
public class Optimum_calories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPTIMUM_CALORIES_ID")
    private Long id;

    private Long age;

    @Column(columnDefinition = "varchar2(1)")
    private String gender;
    private Long calories;

    @ManyToMany(mappedBy = "optimumCaloriesList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> userList;
}
