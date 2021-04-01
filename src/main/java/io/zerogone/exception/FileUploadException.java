package io.zerogone.exception;

public class FileUploadException extends CommonRuntimeException {
    public FileUploadException(Class<?> type, String fileName) {
        super(type, "파일 업로드", fileName);
    }
}
