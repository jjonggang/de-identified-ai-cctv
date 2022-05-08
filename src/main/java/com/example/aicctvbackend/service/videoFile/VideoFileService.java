package com.example.aicctvbackend.service.videoFile;

import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.videoFile.VideoFile;
import com.example.aicctvbackend.domain.videoFile.VideoFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Component
public class VideoFileService {
    private final VideoFileRepository videoFileRepository;

    public Long insertVideoFileList(String fileUrl) {
        String [] list = fileUrl.split("/");
        String fileName = list[list.length - 1];

        VideoFile videoFile = VideoFile.createVideoFile(fileName, fileUrl);

        VideoFile savedFile = videoFileRepository.save(videoFile);
        return savedFile.getFileId();
    }
}
