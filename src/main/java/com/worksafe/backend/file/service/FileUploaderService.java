package com.worksafe.backend.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.worksafe.backend.file.dto.FileUploadModel;

public interface FileUploaderService {

 FileUploadModel upload(MultipartFile file,String path, String fileName);
 FileUploadModel delete(String path, String FileName);
 String getFileSignedUrl(String path, String fileName);
}
