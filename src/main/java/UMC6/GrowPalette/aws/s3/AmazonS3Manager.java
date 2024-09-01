package UMC6.GrowPalette.aws.s3;

import UMC6.GrowPalette.config.AmazonConfig;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file){ return null;}

    public String generateReviewKeyName(Uuid uuid) {
        return amazonConfig.getProfilePath() + '/' + uuid.getUuid();
    }


}
