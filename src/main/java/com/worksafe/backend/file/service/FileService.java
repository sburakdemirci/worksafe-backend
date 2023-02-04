package com.worksafe.backend.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.worksafe.backend.file.dto.FileUploadModel;
import com.worksafe.backend.file.exception.StorageFileNotFoundException;

public interface FileService {

 FileUploadModel upload(MultipartFile file,String bucketName,String path, String fileName);
 boolean delete(String bucketName, String path, String fileName)
         throws StorageFileNotFoundException;
 String getSignedUrl(String bucketName,String path, String fileName)
         throws StorageFileNotFoundException;
}
