package io.zerogone.exception;

public class FileUploadException extends ExceptionTemplate {
    public FileUploadException(Class<?> type, String fileName) {
        super(type, "파일 업로드", fileName);
    }
}
