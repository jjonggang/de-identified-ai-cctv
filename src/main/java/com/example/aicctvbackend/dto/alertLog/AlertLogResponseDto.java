package com.example.aicctvbackend.dto.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlertLogResponseDto {
    private Long userId;
    private EmergencyType emergencyType;
    private CaptureFile captureFile;
    private LocalDateTime createdDate;
    private String upperLeftx;
    private String upperLefty;
    private String bottomRightx;
    private String bottomRighty;


    public AlertLogResponseDto(AlertLog entity){
        this.userId = entity.getUserId();
        this.emergencyType = entity.getEmergencyType();
        this.captureFile = entity.getCaptureFile();
        this.createdDate = entity.getCreatedDate();
        this.upperLeftx = entity.getUpperLeftx();
        this.upperLefty = entity.getUpperLefty();
        this.bottomRightx = entity.getBottomRightx();
        this.bottomRighty = entity.getBottomRighty();
    }
}