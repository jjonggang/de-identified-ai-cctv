package com.example.aicctvbackend.domain.emergencyType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "emergency_type")
public class EmergencyType {
    @Id
    @JsonProperty("type_id")
    private Long typeId;
    @JsonProperty("type_name_eng")
    private String typeNameEng;
    @JsonProperty("type_name_kor")
    private String typeNameKor;
}
