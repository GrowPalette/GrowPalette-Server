package UMC6.GrowPalette.domain.Profile;

import UMC6.GrowPalette.aws.s3.AmazonS3Manager;
import UMC6.GrowPalette.domain.user.User;
import UMC6.GrowPalette.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AmazonS3Manager amazonS3Manager;

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

        // 이미지 파일이 있으면 업로드 후 URL 저장
        if (profileDto.getProfileImageFile() != null && !profileDto.getProfileImageFile().isEmpty()) {
            String keyName = amazonS3Manager.generateProfileKeyName(user.getId());
            String imageUrl = amazonS3Manager.uploadFile(keyName, profileDto.getProfileImageFile());
            profile.setProfileImageUrl(imageUrl);
        }

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
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // 필드 업데이트 로직
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

        // 이미지 파일 처리
        MultipartFile imageFile = profileDto.getProfileImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            // 기존 이미지 삭제 로직 (필요시 구현)
            if (profile.getProfileImageUrl() != null && !profile.getProfileImageUrl().isEmpty()) {
                amazonS3Manager.deleteFile(profile.getProfileImageUrl());
            }
            // 새로운 이미지 업로드
            String keyName = amazonS3Manager.generateProfileKeyName(profile.getUser().getId());
            String imageUrl = amazonS3Manager.uploadFile(keyName, imageFile);
            profile.setProfileImageUrl(imageUrl);
        }

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
                profile.getIntern(),
                profile.getProfileImageUrl(),
                null // MultipartFile 반환 x
        );
    }
}
