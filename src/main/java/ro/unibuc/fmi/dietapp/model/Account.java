package ro.unibuc.fmi.dietapp.model;

import lombok.*;

import javax.persistence.*;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String password;

    private String role;

    @Column(columnDefinition = "varchar2(1)")
    private String active;

    @OneToOne
    private User user;
}
