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
public class DietType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIET_TYPE_ID")
    private Long id;

    @Column(name = "DIET_TYPE_NAME")
    private String name;

    @OneToMany(mappedBy = "dietType", cascade = CascadeType.PERSIST)
    private List<Diet> dietList;
}
