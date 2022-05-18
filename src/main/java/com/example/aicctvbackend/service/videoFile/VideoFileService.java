package com.example.aicctvbackend.service.videoFile;

import com.example.aicctvbackend.domain.amazonS3.videoFile.VideoFile;
import com.example.aicctvbackend.domain.amazonS3.videoFile.VideoFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
