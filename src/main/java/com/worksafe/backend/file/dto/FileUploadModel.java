package com.worksafe.backend.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class FileUploadModel {

    private String bucket;
    private String path;
    private String fileName;
    private String contentType;
    private Long size;

}
