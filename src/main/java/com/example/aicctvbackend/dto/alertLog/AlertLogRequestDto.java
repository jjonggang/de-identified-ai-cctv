package com.example.aicctvbackend.dto.alertLog;

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
    private Long videoFileId;
    private Long classroomId;
    private int numOfParticipant;
    private Double upperLeftx;
    private Double upperLefty;
    private Double bottomRightx;
    private Double bottomRighty;
}
