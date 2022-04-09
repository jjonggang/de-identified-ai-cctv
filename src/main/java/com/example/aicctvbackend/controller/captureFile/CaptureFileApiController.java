package com.example.aicctvbackend.controller.captureFile;

import com.example.aicctvbackend.service.captureFile.CaptureFileService;
import com.example.aicctvbackend.service.captureFile.S3Uploader;
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

    private final CaptureFileService captureFileService;
    private final S3Uploader s3Uploader;

    @PostMapping("/file/capture/upload")
    public Long captureUpload(@RequestParam("file") MultipartFile file) throws Exception{
        Long fileId = s3Uploader.upload(file, "static");
        return fileId;
    }

    @GetMapping("/file/capture/download")
    public ResponseEntity<Resource> serveFile(@RequestParam(value="filename") String filename) {

        Resource file = captureFileService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
