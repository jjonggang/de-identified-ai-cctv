package com.example.aicctvbackend.domain.amazonS3.captureFile;

import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "capture_file")
public class CaptureFile extends CreateTimeEntity {
    @Id
    @JsonProperty("file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("file_path")
    private String filePath;

    public static CaptureFile createCaptureFile(String fileName, String fileUrl) {
        CaptureFile captureFile = new CaptureFile();
        captureFile.setFileName(fileName);
        captureFile.setFilePath(fileUrl);
        return captureFile;
    }
}
