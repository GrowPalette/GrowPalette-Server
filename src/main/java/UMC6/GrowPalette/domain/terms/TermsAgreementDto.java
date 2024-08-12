package UMC6.GrowPalette.domain.terms;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermsAgreementDto {
    private Long userId;
    private boolean isOver14;
    private boolean serviceTermsAgreed;
    private boolean personalInfoAgreed;
    private boolean marketingAgreed;

}
