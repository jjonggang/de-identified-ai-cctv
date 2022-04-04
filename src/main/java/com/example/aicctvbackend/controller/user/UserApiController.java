package com.example.aicctvbackend.controller.user;

import com.example.aicctvbackend.domain.user.User;
import com.example.aicctvbackend.dto.user.UserDto;
import com.example.aicctvbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        try{
            // 요청을 이용해 저장할 사용자 만들기
            User user = User.builder()
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .build();
            // 서비스를 이용해 리포지터리에 사용자 저장
            User registeredUser = userService.create(user);
            UserDto responseUserDto = UserDto.builder()
                    .email(registeredUser.getEmail())
                    .userId(registeredUser.getUserId())
                    .username(registeredUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDto);
        } catch (Exception e){
            ResponseDto responseDto = ResponseDt
        }
    }
}
