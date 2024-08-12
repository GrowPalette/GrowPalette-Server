package UMC6.GrowPalette.domain.Profile;

import UMC6.GrowPalette.common.BaseEntity;
import UMC6.GrowPalette.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "profiles")
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Univ univ;

    @Enumerated(EnumType.STRING)
    @Column(name = "univ_status", nullable = false)
    private UnivStatus univStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Year year;

    @Column(length = 255)
    private String major;

    @Column
    private Double gpa;

    @Column(length = 255)
    private String language;

    @Column(length = 255)
    private String job;

    @Column(length = 500)
    private String interests;

    private Integer certificate;

    private Integer competition;

    private Integer intern;

}

enum Univ {
    TWO_YEAR, THREE_YEAR, FOUR_YEAR;
}

enum UnivStatus {
    ENROLLED, GRADUATED, LEAVE_OF_ABSENCE;
}

enum Year {
    FRESHMAN, SOPHOMORE, JUNIOR, SENIOR;
}

