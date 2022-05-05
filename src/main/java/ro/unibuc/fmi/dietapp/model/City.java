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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CITY_ID")
    private Long id;

    @Column(name = "CITY_NAME")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<User> userList;

    @ManyToOne
    private Country country;
}
