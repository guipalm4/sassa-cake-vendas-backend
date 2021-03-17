package com.sassacakes.sales.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sassacakes.sales.core.dto.UploadFileRequest;
import com.sassacakes.sales.core.exception.BusinessException;
import com.sassacakes.sales.core.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    public URI uploadFile(UploadFileRequest uploadFileRequest) throws IOException {
        try {
            if (Objects.isNull(uploadFileRequest.getMultipartFile())) {
                throw new BusinessException("Nenhum arquivo selecionado");
            }
            String fileName = this.generateFileName(uploadFileRequest);
            InputStream is = uploadFileRequest.getMultipartFile().getInputStream();
            String contentType = uploadFileRequest.getMultipartFile().getContentType();
            return uploadFile(is, fileName, contentType, uploadFileRequest.getBucketName());
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }

    }

    public URI uploadFile(InputStream is, String fileName, String contentType, String bucketName) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            LOG.info("Iniciando upload");
            s3client.putObject(bucketName, fileName, is, meta);
            LOG.info("Upload finalizado");
            return s3client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }


    private String generateFileName(UploadFileRequest uploadFileRequest) {
        StringBuilder fileName = new StringBuilder();

        if (Objects.nonNull(uploadFileRequest.getFolder())) {
            fileName.append(uploadFileRequest.getFolder()).append("/");
        }

        if (Objects.nonNull(uploadFileRequest.getFileName())) {
            return fileName.append(uploadFileRequest.getFileName()).toString();
        }
        return fileName.append(uploadFileRequest.getMultipartFile().getOriginalFilename()).toString();

    }
}
