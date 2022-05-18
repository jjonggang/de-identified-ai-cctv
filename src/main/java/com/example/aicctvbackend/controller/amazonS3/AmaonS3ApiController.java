package com.example.aicctvbackend.controller.amazonS3;


import com.example.aicctvbackend.service.awsS3.AwsS3Service;
import com.example.aicctvbackend.service.captureFile.CaptureFileService;
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
public class AmaonS3ApiController {

    private final CaptureFileService captureFileService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/file/delete")
    public void captureUpload2(@RequestParam("file_name") String fileName) throws Exception{
        log.info(fileName);
        awsS3Service.deleteFile(fileName);
    }
}
