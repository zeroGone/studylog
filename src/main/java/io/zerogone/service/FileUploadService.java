package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.zerogone.exception.FileUploadException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public FileUploadService(AmazonS3 amazonS3, @Value("${aws.s3.bucketname}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public String uploadFile(String path, MultipartFile multipartFile) {
        logger.info("-----file upload start-----");

        if (multipartFile == null) {
            logger.debug("file is not existed. it returns null");
            return null;
        }

        String filePath = path + "/" + multipartFile.getOriginalFilename();
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, filePath, multipartFile.getInputStream(), null));
        } catch (IOException ioException) {
            logger.error("Uploading file is failed!");
            throw new FileUploadException(MultipartFile.class, multipartFile.getName());
        }

        logger.info("-----file upload end-----");
        return amazonS3.getUrl(bucketName, filePath).toString();
    }
}
