package com.example.aicctvbackend.service.captureFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.videoFile.VideoFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CaptureFileService {
    private final CaptureFileRepository captureFileRepository;

    public Long insertCaptureFileList(String fileUrl) {

        String [] list = fileUrl.split("/");
        String fileName = list[list.length - 1];

        CaptureFile captureFile = CaptureFile.createCaptureFile(fileName, fileUrl);

        CaptureFile savedFile = captureFileRepository.save(captureFile);
        return savedFile.getFileId();
    }
}