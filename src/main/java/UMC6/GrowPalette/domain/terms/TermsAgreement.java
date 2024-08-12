package UMC6.GrowPalette.domain.terms;

import UMC6.GrowPalette.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "terms_agreement")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "termsAgreement", fetch = FetchType.LAZY)
    private User user;

    private boolean isOver14; // 만 14세 이상 여부
    private boolean serviceTermsAgreed; // 서비스 이용약관 동의 여부
    private boolean personalInfoAgreed; // 개인정보 수집 및 이용 동의 여부
    private boolean marketingAgreed; // 광고 및 마케팅 수신 동의 여부 (선택)

    @Builder
    public TermsAgreement(User user, boolean isOver14, boolean serviceTermsAgreed, boolean personalInfoAgreed, boolean marketingAgreed) {
        this.user = user;
        this.isOver14 = isOver14;
        this.serviceTermsAgreed = serviceTermsAgreed;
        this.personalInfoAgreed = personalInfoAgreed;
        this.marketingAgreed = marketingAgreed;
    }
}
