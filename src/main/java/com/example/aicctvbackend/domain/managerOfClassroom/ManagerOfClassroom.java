package com.example.aicctvbackend.domain.managerOfClassroom;

import com.example.aicctvbackend.domain.classroom.Classroom;
import com.example.aicctvbackend.domain.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "manager_of_classroom")
public class ManagerOfClassroom {
    @Id
    @JsonProperty("manager_of_classroom_id")
    private Long managerOfClassroomId;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonProperty("user_id")
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "classroom_id")
//    @JsonProperty("classroom_id")
//    private Classroom classroom;
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("classroom_id")
    private Long classroomId;
}
