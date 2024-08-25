package UMC6.GrowPalette.domain.Profile;

import UMC6.GrowPalette.domain.user.User;
import UMC6.GrowPalette.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileDto createProfile(ProfileDto profileDto) {
        User user = userRepository.findById(profileDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setUniv(profileDto.getUniv());
        profile.setUnivStatus(profileDto.getUnivStatus());
        profile.setYear(profileDto.getYear());
        profile.setMajor(profileDto.getMajor());
        profile.setGpa(profileDto.getGpa());
        profile.setLanguage(profileDto.getLanguage());
        profile.setJob(profileDto.getJob());
        profile.setInterests(profileDto.getInterests());
        profile.setCertificate(profileDto.getCertificate());
        profile.setCompetition(profileDto.getCompetition());
        profile.setIntern(profileDto.getIntern());

        Profile savedProfile = profileRepository.save(profile);
        return convertToDto(savedProfile);
    }

    public ProfileDto getProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElse(null);

        if (profile == null) {
            return null;
        }

        return convertToDto(profile);
    }

    public ProfileDto updateProfile(Long profileId, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(profileId)
                .orElse(null);

        if (profile == null) {
            return null;
        }

        // 필요한 필드 업데이트
        if (profileDto.getUniv() != null) profile.setUniv(profileDto.getUniv());
        if (profileDto.getUnivStatus() != null) profile.setUnivStatus(profileDto.getUnivStatus());
        if (profileDto.getYear() != null) profile.setYear(profileDto.getYear());
        if (profileDto.getMajor() != null) profile.setMajor(profileDto.getMajor());
        if (profileDto.getGpa() != null) profile.setGpa(profileDto.getGpa());
        if (profileDto.getLanguage() != null) profile.setLanguage(profileDto.getLanguage());
        if (profileDto.getJob() != null) profile.setJob(profileDto.getJob());
        if (profileDto.getInterests() != null) profile.setInterests(profileDto.getInterests());
        if (profileDto.getCertificate() != null) profile.setCertificate(profileDto.getCertificate());
        if (profileDto.getCompetition() != null) profile.setCompetition(profileDto.getCompetition());
        if (profileDto.getIntern() != null) profile.setIntern(profileDto.getIntern());

        Profile updatedProfile = profileRepository.save(profile);
        return convertToDto(updatedProfile);
    }

    private ProfileDto convertToDto(Profile profile) {
        return new ProfileDto(
                profile.getUser().getId(),
                profile.getUniv(),
                profile.getUnivStatus(),
                profile.getYear(),
                profile.getMajor(),
                profile.getGpa(),
                profile.getLanguage(),
                profile.getJob(),
                profile.getInterests(),
                profile.getCertificate(),
                profile.getCompetition(),
                profile.getIntern()
        );
    }
}
