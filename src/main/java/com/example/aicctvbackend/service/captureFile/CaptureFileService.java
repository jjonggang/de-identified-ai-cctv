package com.example.aicctvbackend.service.captureFile;

import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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