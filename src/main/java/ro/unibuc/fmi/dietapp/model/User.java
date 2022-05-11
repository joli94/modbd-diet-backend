package ro.unibuc.fmi.dietapp.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USERS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    private String first_name;
    private String last_name;

    private String target;

    @Column(columnDefinition = "varchar2(1)")
    private String gender;

    @Column(unique = true)
    private String username;

    private LocalDate birth_date;
    private String cnp;
    private String cardNumber;

    @Column(columnDefinition = "varchar2(4) default 'none'")
    private String isAdmin;

    @ManyToMany
    @JoinTable(name = "USER_OPTIMUMS",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "OPTIMUM_CALORIES_ID", referencedColumnName = "OPTIMUM_CALORIES_ID")
    )
    private List<Optimum_calories> optimumCaloriesList;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Weight> weightList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Billing> billingList;
}
