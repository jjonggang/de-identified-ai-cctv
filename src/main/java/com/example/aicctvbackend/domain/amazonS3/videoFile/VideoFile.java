package com.example.aicctvbackend.domain.amazonS3.videoFile;

import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "video_file")
public class VideoFile extends CreateTimeEntity {
    @Id
    @JsonProperty("file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("file_path")
    private String filePath;

    public static VideoFile createVideoFile(String fileName, String fileUrl) {
        VideoFile videoFile = new VideoFile();
        videoFile.setFileName(fileName);
        videoFile.setFilePath(fileUrl);
        return videoFile;
    }

}
