package ro.unibuc.fmi.dietapp.model;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Entity
@Immutable

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

    @Column(name = "COUNTRY_REGION", columnDefinition = "varchar2(1)")
    private String region;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<City> cityList;
}
