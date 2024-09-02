package UMC6.GrowPalette.domain.Profile;

import UMC6.GrowPalette.apiPayload.ApiResponse;
import UMC6.GrowPalette.apiPayload.status.SuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    // 프로필 생성
    @PostMapping(consumes = {"multipart/form-data"})
    public ApiResponse<ProfileDto> createProfile(@ModelAttribute @Valid ProfileDto profileDto) {
        ProfileDto createdProfile = profileService.createProfile(profileDto);
        return ApiResponse.onSuccess(SuccessStatus.ProfileCreate_OK, createdProfile);
    }

    // 프로필 조회
    @GetMapping("/{profileId}")
    public ApiResponse<ProfileDto> getProfile(@PathVariable Long profileId) {
        ProfileDto profileDto = profileService.getProfile(profileId);
        if (profileDto != null) {
            return ApiResponse.onSuccess(SuccessStatus.ProfileGet_OK, profileDto);
        } else {
            return ApiResponse.onFailure("PROFILE_NOT_FOUND", "프로필을 찾을 수 없습니다.", null);
        }
    }

    // 프로필 수정
    @PatchMapping(value = "/{profileId}", consumes = {"multipart/form-data"})
    public ApiResponse<ProfileDto> updateProfile(
            @PathVariable Long profileId,
            @ModelAttribute @Valid ProfileDto profileDto) {
        ProfileDto updatedProfile = profileService.updateProfile(profileId, profileDto);
        if (updatedProfile != null) {
            return ApiResponse.onSuccess(SuccessStatus.ProfilePatch_OK, updatedProfile);
        } else {
            return ApiResponse.onFailure("PROFILE_NOT_FOUND", "프로필을 찾을 수 없습니다.", null);
        }
    }
}
