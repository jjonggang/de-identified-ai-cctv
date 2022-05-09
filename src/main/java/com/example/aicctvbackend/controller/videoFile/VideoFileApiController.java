package com.example.aicctvbackend.controller.videoFile;

import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.dto.response.ResponsePageDto;
import com.example.aicctvbackend.service.awsS3.AwsS3Service;
import com.example.aicctvbackend.service.videoFile.VideoFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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

//    @GetMapping("/file/video/list")
//    public ResponseEntity<?> getVideoList(@AuthenticationPrincipal String strUserId, @PageableDefault(page = 0, size=10) Pageable pageable){
//        Long userId = Long.parseLong(strUserId);
//        ResponsePageDto<AlertLogResponseDto> response = alertLogService.getAlertLogByUserId(userId, pageable);
//
//        return ResponseEntity.ok().body(response);
//    }
}
