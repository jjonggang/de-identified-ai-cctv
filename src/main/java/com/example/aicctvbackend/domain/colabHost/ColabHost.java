package com.example.aicctvbackend.domain.colabHost;

import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "colab_host")
public class ColabHost extends CreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colabHostId;

    private String host;

    @JsonProperty("port_number")
    private String portNumber;

    @JsonProperty("camera_id")
    private Long cameraId;

    public ColabHost(String host, String portNumber, Long cameraId){
        this.host = host;
        this.portNumber = portNumber;
        this.cameraId = cameraId;
    }


}
