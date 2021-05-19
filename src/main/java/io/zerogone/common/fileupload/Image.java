package io.zerogone.common.fileupload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class Image {
    private final String path;
    private final MultipartFile data;

    public Image(String savingPath, MultipartFile image) {
        path = savingPath + "/" + image.getOriginalFilename();
        data = image;
    }

    public String getPath() {
        return path;
    }

    public InputStream getInputStream() throws IOException {
        return data.getInputStream();
    }
}
