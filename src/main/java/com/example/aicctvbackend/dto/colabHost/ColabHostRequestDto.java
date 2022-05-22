package com.example.aicctvbackend.dto.colabHost;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ColabHostRequestDto {
    private Long cameraId;
    private String host;
    private String portNumber;
}