package com.worksafe.backend.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.worksafe.backend.file.dto.FileUploadModel;

@Service
public class FileUploaderServiceImpl implements FileUploaderService {


    @Override
    public FileUploadModel upload(MultipartFile file, String path, String fileName) {
        return null;
    }

    @Override
    public FileUploadModel delete(String path, String FileName) {
        return null;
    }

    @Override
    public String getFileSignedUrl(String path, String fileName) {
        return null;
    }
}
