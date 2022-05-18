package com.example.aicctvbackend.bank;

import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptureFileService2 {
    private final CaptureFileRepository captureFileRepository;

    public Long fileWrite(MultipartFile file) throws Exception{
        CaptureFile inputFile = new CaptureFile();

        // 프로젝트의 경로가 담긴다.
        String projectPath = System.getProperty("user.dir") + "/capture-file";
//        String projectPath = System.getProperty("user.dir") + "/capture-files";

        log.info(System.getProperty(projectPath));
        //\src\main\resources\static\files
        // 파일을 생성할 건데, projectPath 경로에 넣을 거고 이름은 두번째 인자처럼 담긴다.
        // 들어올 파일을 넣을 빈 껍데기 생성

        UUID uuid = UUID.randomUUID(); // 랜덤으로 식별자 만들기
        // 랜덤 식별자_원래파일이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        inputFile.setFileName(fileName);
        inputFile.setFilePath("http://ec2-3-36-163-212.ap-northeast-2.compute.amazonaws.com:8080/capture-file/view/"+ fileName);
        CaptureFile savedFile = captureFileRepository.save(inputFile);
        return savedFile.getFileId();
    }

    public Path load(String filename){
//        return Paths.get(System.getProperty("user.dir") + "/spring-boot-de-identified-ai-cctv" +"/src/main/resources/static/files").resolve(filename);
        return Paths.get(System.getProperty("user.dir") + "/capture-file").resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}
