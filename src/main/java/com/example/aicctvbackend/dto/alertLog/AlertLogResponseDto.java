package com.example.aicctvbackend.dto.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.classroom.Classroom;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.example.aicctvbackend.domain.videoFile.VideoFile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlertLogResponseDto {
    private Long alertLogId;
    private EmergencyType emergencyType;
    private CaptureFile captureFile;
    private VideoFile videoFile;
    private Classroom classroom;
    private int numOfParticipant;
    private LocalDateTime createdDate;
    private Double upperLeftx;
    private Double upperLefty;
    private Double bottomRightx;
    private Double bottomRighty;


    public AlertLogResponseDto(AlertLog entity){
        this.alertLogId = entity.getLogId();
        this.emergencyType = entity.getEmergencyType();
        this.captureFile = entity.getCaptureFile();
        this.videoFile = entity.getVideoFile();
        this.classroom = entity.getClassroom();
        this.numOfParticipant = entity.getNumOfParticipant();
        this.createdDate = entity.getCreatedDate();
        this.upperLeftx = entity.getUpperLeftx();
        this.upperLefty = entity.getUpperLefty();
        this.bottomRightx = entity.getBottomRightx();
        this.bottomRighty = entity.getBottomRighty();
    }
}