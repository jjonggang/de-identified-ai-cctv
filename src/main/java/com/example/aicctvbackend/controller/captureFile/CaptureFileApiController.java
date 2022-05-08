package com.example.aicctvbackend.controller.captureFile;

import com.example.aicctvbackend.bank.CaptureFileService2;
import com.example.aicctvbackend.service.awsS3.AwsS3Service;
import com.example.aicctvbackend.service.captureFile.CaptureFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class CaptureFileApiController {

//    private final CaptureFileService2 captureFileService;
    private final CaptureFileService captureFileService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/file/capture/upload")
    public Long captureUpload2(@RequestParam("file") MultipartFile file) throws Exception{
        String fileUrl = awsS3Service.upload2(file, "capture-file");
        Long fileId = captureFileService.insertCaptureFileList(fileUrl);
        return fileId;
    }

//    @GetMapping("/file/capture/download")
//    public ResponseEntity<Resource> serveFile(@RequestParam(value="filename") String filename) {
//
//        Resource file = captureFileService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
}
