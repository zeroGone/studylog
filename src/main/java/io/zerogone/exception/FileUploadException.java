package io.zerogone.exception;

public class FileUploadException extends CustomRuntimeException {
    public FileUploadException(String fileName) {
        super("파일 업로드 실패", fileName);
    }
}
