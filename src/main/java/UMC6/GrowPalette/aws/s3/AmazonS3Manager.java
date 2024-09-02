package UMC6.GrowPalette.aws.s3;

import UMC6.GrowPalette.config.AmazonConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    //파일 업로드
    public String uploadFile(String keyName, MultipartFile file) {
        // ObjectMetadata 생성 및 파일 크기 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // Content-Type 설정
        String contentType = file.getContentType();
        if (contentType != null) {
            metadata.setContentType(contentType);
        } else {
            // 만약 getContentType()이 null을 반환한다면, 기본적인 이미지 MIME 타입 설정
            metadata.setContentType("application/octet-stream");
        }

        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error("Error uploading file to S3: {}", e.getMessage());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    // 기존 이미지 삭제 메서드
    public void deleteFile(String fileUrl) {
        // S3에서의 파일 경로 추출
        String bucketName = amazonConfig.getBucket();
        String keyName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1); // 파일 URL에서 키를 추출
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, keyName));
            log.info("Successfully deleted file from S3: {}", fileUrl);
        } catch (Exception e) {
            log.error("Error deleting file from S3: {}", e.getMessage());
        }
    }

    public String generateProfileKeyName(Long userId) {
        return amazonConfig.getProfilePath() + "/profile_" + userId;
    }


}
