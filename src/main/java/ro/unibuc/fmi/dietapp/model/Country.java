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
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COUNTRY_ID")
    private Long id;

    @Column(name = "COUNTRY_NAME")
    private String name;

    @Column(name = "COUNTRY_CODE", columnDefinition = "varchar2(2)")
    private String code;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<City> cityList;
}
