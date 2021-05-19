package io.zerogone.common.fileupload;

import io.zerogone.common.exception.CustomRuntimeException;

public class FileUploadException extends CustomRuntimeException {
    public FileUploadException(String fileName) {
        super("파일 업로드 실패", fileName);
    }
}
