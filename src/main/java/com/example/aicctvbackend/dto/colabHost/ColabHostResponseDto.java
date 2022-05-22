package com.example.aicctvbackend.dto.colabHost;

import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.amazonS3.videoFile.VideoFile;
import com.example.aicctvbackend.domain.colabHost.ColabHost;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ColabHostResponseDto {
    private Long cameraId;
    private String host;
    private String portNumber;

    public ColabHostResponseDto(ColabHost colabHost){
        this.cameraId = colabHost.getCameraId();
        this.host = colabHost.getHost();
        this.portNumber = colabHost.getPortNumber();
    }
}
