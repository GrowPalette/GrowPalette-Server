package UMC6.GrowPalette.domain.terms;


import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terms")
public class TermsAgreementController {
    private final TermsAgreementService termsAgreementService;

    public TermsAgreementController(TermsAgreementService termsAgreementService) {
        this.termsAgreementService = termsAgreementService;
    }

    @PostMapping("/agree")
    public ApiResponse<TermsAgreementDto> agreeToTerms(@RequestBody TermsAgreementDto termsAgreementDto) {
        if (termsAgreementDto.getUserId() == null) {
            return ApiResponse.onFailure("USER_ID_NULL", "User ID must not be null", null);
        }

        TermsAgreement termsAgreement = termsAgreementService.saveTermsAgreement(
                termsAgreementDto.getUserId(),
                termsAgreementDto.isOver14(),
                termsAgreementDto.isServiceTermsAgreed(),
                termsAgreementDto.isPersonalInfoAgreed(),
                termsAgreementDto.isMarketingAgreed()
        );

        TermsAgreementDto responseDto = new TermsAgreementDto(
                termsAgreement.getUser().getId(),
                termsAgreement.isOver14(),
                termsAgreement.isServiceTermsAgreed(),
                termsAgreement.isPersonalInfoAgreed(),
                termsAgreement.isMarketingAgreed()
        );

        return ApiResponse.onSuccess(SuccessStatus.Activity_OK, responseDto);
    }

    @GetMapping("/{userId}")
    public ApiResponse<TermsAgreementDto> getTermsAgreement(@PathVariable Long userId) {
        return termsAgreementService.getTermsAgreementByUserId(userId)
                .map(termsAgreement -> {
                    TermsAgreementDto responseDto = new TermsAgreementDto(
                            termsAgreement.getUser().getId(),
                            termsAgreement.isOver14(),
                            termsAgreement.isServiceTermsAgreed(),
                            termsAgreement.isPersonalInfoAgreed(),
                            termsAgreement.isMarketingAgreed()
                    );
                    return ApiResponse.onSuccess(SuccessStatus.Activity_OK, responseDto);
                })
                .orElseGet(() -> ApiResponse.onFailure("TERMS_NOT_FOUND", "Terms agreement not found for the given user ID", null));
    }
}
