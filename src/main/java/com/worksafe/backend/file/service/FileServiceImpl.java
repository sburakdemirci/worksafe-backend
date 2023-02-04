package com.worksafe.backend.file.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobWriteOption;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.worksafe.backend.file.configuration.FileServiceProperties;
import com.worksafe.backend.file.dto.FileUploadModel;
import com.worksafe.backend.file.exception.StorageFileNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final Storage storage;
    private final FileServiceProperties fileServiceProperties;
    private static final long DIVIDE_BYTES_TO_KILOBYTES = 1024;

    @Override
    public FileUploadModel upload(MultipartFile file, String bucketName, String path,
            String fileName) {

        try {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, mergePath(path, fileName)).build();

            Blob blob = storage.createFrom(blobInfo, new ByteArrayInputStream(file.getBytes()),
                    BlobWriteOption.detectContentType());

            return new FileUploadModel(blob.getBucket(), path, fileName, blob.getContentType(),
                    convertSizeToKilobytes(blob.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String bucketName, String path, String fileName)
            throws StorageFileNotFoundException {

        Blob blob = getFile(bucketName, path, fileName)
                .orElseThrow(() -> new StorageFileNotFoundException(
                        String.format("File does not found. Bucket: %s  Path: %s  FileName: %s",
                                bucketName, path, fileName)));
        return blob.delete();
    }

    @Override
    public String getSignedUrl(String bucketName, String path, String fileName)
            throws StorageFileNotFoundException {

        Blob blob = getFile(bucketName, path, fileName)
                .orElseThrow(() -> new StorageFileNotFoundException(
                        String.format("File does not found. Bucket: %s  Path: %s  FileName: %s",
                                bucketName, path, fileName)));

        URL url = blob.signUrl(fileServiceProperties.getSignedUrlDurationSeconds(),
                TimeUnit.SECONDS,
                SignUrlOption.withV4Signature());

        return url.toString();
    }

    private Optional<Blob> getFile(String bucketName, String path, String fileName) {
        return Optional.ofNullable(storage.get(bucketName, mergePath(path, fileName)));
    }


    private String mergePath(String path, String fileName) {
        return path + "/" + fileName;
    }

    private Long convertSizeToKilobytes(Long size) {
        return size / DIVIDE_BYTES_TO_KILOBYTES;
    }


    /*        storage.signUrl(blobInfo, fileServiceProperties.getSignedUrlDurationSeconds(),
                TimeUnit.SECONDS,
                Storage.SignUrlOption.withV4Signature());*/
         /*   BlobId blobId = BlobId.of(bucketName, path + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();*/

}
