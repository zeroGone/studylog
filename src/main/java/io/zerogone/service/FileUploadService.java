package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.FileUploadException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private static final String TEMPORARY_FILE_UPLOAD_PATH = "C://tmp";

    public String uploadFile(MultipartFile multipartFile) {
        logger.info("-----file upload start-----");
        if (multipartFile == null) {
            logger.debug("file is not existed. it returns null");
            return null;
        }

        File file = new File(TEMPORARY_FILE_UPLOAD_PATH, multipartFile.getOriginalFilename());
        logger.debug("create uploaded file : " + file);

        try {
            multipartFile.transferTo(file);
            logger.info("-----file upload end-----");
        } catch (IOException ioException) {
            logger.error("Uploading file is failed!");
            throw new FileUploadException("파일 업로드에 실패하였습니다! 원인 : [" + ioException.getMessage() + "]");
        }
        return file.getAbsolutePath();
    }
}
