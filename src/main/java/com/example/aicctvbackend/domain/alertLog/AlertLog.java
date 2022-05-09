package com.example.aicctvbackend.domain.alertLog;


import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.classroom.Classroom;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.example.aicctvbackend.domain.videoFile.VideoFile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "alert_log")
public class AlertLog extends CreateTimeEntity {
    @Id
    @JsonProperty("log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonProperty("type_id")
    private EmergencyType emergencyType;
    @OneToOne
    @JoinColumn(name = "capture_file_id")
    @JsonProperty("capture_file_id")
    private CaptureFile captureFile;
    @OneToOne
    @JoinColumn(name = "video_file_id")
    @JsonProperty("video_file_id")
    private VideoFile videoFile;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    @JsonProperty("num_of_participant")
    private int numOfParticipant;
    @JsonProperty("upper_leftx")
    private Double upperLeftx;
    @JsonProperty("upper_lefty")
    private Double upperLefty;
    @JsonProperty("bottom_rightx")
    private Double bottomRightx;
    @JsonProperty("bottom_righty")
    private Double bottomRighty;



}
