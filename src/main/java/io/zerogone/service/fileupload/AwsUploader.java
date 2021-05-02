package io.zerogone.service.fileupload;

import ch.qos.logback.classic.Logger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AwsUploader {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public AwsUploader(AmazonS3 amazonS3, @Value("${aws.s3.bucketname}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public ImageUrl upload(Image image) {
        logger.info("-----Uploading file to AWS s3 start-----");
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, image.getPath(), image.getInputStream(), null));
        } catch (IOException ioException) {
            logger.error("Uploading file is failed!");
        }
        logger.info("-----Uploading file to AWS s3 is ended-----");
        return new ImageUrl(amazonS3.getUrl(bucketName, image.getPath()).toString());
    }
}
