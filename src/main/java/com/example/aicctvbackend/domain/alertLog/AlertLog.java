package com.example.aicctvbackend.domain.alertLog;


import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.example.aicctvbackend.domain.captureFile.CaptureFile;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
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
    @JsonProperty("user_id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonProperty("type_id")
    private EmergencyType emergencyType;
    @OneToOne
    @JoinColumn(name = "file_id")
    @JsonProperty("file_id")
    private CaptureFile captureFile;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;
    private Long classroom;
    @JsonProperty("participant_number")
    private Long participantNumber;
    @JsonProperty("upper_leftx")
    private Double upperLeftx;
    @JsonProperty("upper_lefty")
    private Double upperLefty;
    @JsonProperty("bottom_rightx")
    private Double bottomRightx;
    @JsonProperty("bottom_righty")
    private Double bottomRighty;
}
