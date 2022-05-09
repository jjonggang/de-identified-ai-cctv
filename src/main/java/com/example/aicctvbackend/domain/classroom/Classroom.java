package com.example.aicctvbackend.domain.classroom;

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
public class Classroom {
    @Id
    @JsonProperty("classroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;
    @JsonProperty("classroom_name")
    private String classroomName;
    @JsonProperty("num_of_student")
    private int numOfStudent;


}
