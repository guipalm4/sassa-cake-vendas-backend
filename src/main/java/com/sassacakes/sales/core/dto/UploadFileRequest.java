package com.sassacakes.sales.core.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileRequest {

    private final MultipartFile multipartFile;
    private final String bucketName;
    private String folder;
    private String fileName;

    public UploadFileRequest(MultipartFile multipartFile, String bucketName) {
        this.multipartFile = multipartFile;
        this.bucketName = bucketName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
