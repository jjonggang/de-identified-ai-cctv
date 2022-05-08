package com.example.aicctvbackend.controller.videoFile;

import com.example.aicctvbackend.service.awsS3.AwsS3Service;
import com.example.aicctvbackend.service.videoFile.VideoFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class VideoFileApiController {
    private final VideoFileService videoFileService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/file/video/upload")
    public Long captureUpload2(@RequestParam("file") MultipartFile file) throws Exception{
        String fileUrl = awsS3Service.upload2(file, "video-file");
        Long fileId = videoFileService.insertVideoFileList(fileUrl);
        return fileId;
    }
}
