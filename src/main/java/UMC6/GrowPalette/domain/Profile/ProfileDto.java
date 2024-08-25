package UMC6.GrowPalette.domain.Profile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private Long userId;
    private Univ univ;
    private UnivStatus univStatus;
    private Year year;
    private String major;
    private Double gpa;
    private String language;
    private String job;
    private String interests;
    private Integer certificate;
    private Integer competition;
    private Integer intern;
}
