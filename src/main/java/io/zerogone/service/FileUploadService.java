package io.zerogone.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
    private final Log logger = LogFactory.getLog(this.getClass());

    private static final String TEMPORARY_FILE_UPLOAD_PATH = "/img/tmp";

    public String uploadFile(String savingPath, MultipartFile multipartFile) {
        logger.info("-----file upload start-----");

        File file = new File(savingPath + TEMPORARY_FILE_UPLOAD_PATH, multipartFile.getOriginalFilename());
        logger.debug("create uploaded file : " + file);

        try {
            multipartFile.transferTo(file);
            logger.info("-----file upload end-----");
        } catch (IOException ioException) {
            logger.error("uploading file is failed!");
            ioException.printStackTrace();
        }
        return TEMPORARY_FILE_UPLOAD_PATH + "/" + multipartFile.getOriginalFilename();
    }
}
