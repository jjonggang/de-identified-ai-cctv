package com.example.aicctvbackend.dto.alertLog;

import com.example.aicctvbackend.domain.captureFile.CaptureFile;
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
public class AlertLogRequestDto {
    private Long emergencyId;
    private Long captureFileId;
    private Double upperLeftx;
    private Double upperLefty;
    private Double bottomRightx;
    private Double bottomRighty;
}
