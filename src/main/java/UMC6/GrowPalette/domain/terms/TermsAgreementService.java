package UMC6.GrowPalette.domain.terms;

import UMC6.GrowPalette.domain.user.User;
import UMC6.GrowPalette.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TermsAgreementService {

    private final TermsAgreementRepository termsAgreementRepository;
    private final UserRepository userRepository;

    public TermsAgreementService(TermsAgreementRepository termsAgreementRepository, UserRepository userRepository) {
        this.termsAgreementRepository = termsAgreementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TermsAgreement saveTermsAgreement(Long userId, boolean isOver14, boolean serviceTermsAgreed, boolean personalInfoAgreed, boolean marketingAgreed) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TermsAgreement termsAgreement = TermsAgreement.builder()
                .user(user)
                .isOver14(isOver14)
                .serviceTermsAgreed(serviceTermsAgreed)
                .personalInfoAgreed(personalInfoAgreed)
                .marketingAgreed(marketingAgreed)
                .build();

        user.setTermsAgreement(termsAgreement);

        termsAgreementRepository.save(termsAgreement);
        userRepository.save(user);

        return termsAgreement;
    }

    public Optional<TermsAgreement> getTermsAgreementByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getTermsAgreement);
    }
}
