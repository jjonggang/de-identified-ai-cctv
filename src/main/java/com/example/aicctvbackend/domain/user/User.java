package com.example.aicctvbackend.domain.user;

import com.example.aicctvbackend.domain.CreateTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)       //snake
public class User extends CreateTimeEntity {
    @Id
    @JsonProperty("user_id")
    private Long userID;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("user_email")
    private String userEmail;
}
