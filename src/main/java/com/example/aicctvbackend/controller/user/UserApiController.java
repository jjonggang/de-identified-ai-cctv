package com.example.aicctvbackend.controller.user;

import com.example.aicctvbackend.domain.user.User;
import com.example.aicctvbackend.dto.response.ResponseDto;
import com.example.aicctvbackend.dto.user.UserDto;
import com.example.aicctvbackend.security.TokenProvider;
import com.example.aicctvbackend.service.user.UserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final TokenProvider tokenProvider;

    private final UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/no-login/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        try{
            // 요청을 이용해 저장할 사용자 만들기
            User user = User.builder()
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .phoneNumber(userDto.getPhoneNumber())
                    .build();
            // 서비스를 이용해 리포지터리에 사용자 저장
            User registeredUser = userService.create(user);
            UserDto responseUserDto = UserDto.builder()
                    .email(registeredUser.getEmail())
                    .userId(registeredUser.getUserId())
                    .username(registeredUser.getUsername())
                    .phoneNumber(userDto.getPhoneNumber())
                    .build();

            return ResponseEntity.ok().body(responseUserDto);
        }catch(Exception e){
            //사용자 정보는 항상 하나이므로 리스트를 만들어야하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴

            ResponseDto responseDTO = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping("/no-login/auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDto userDto) {
        User user = userService.getByCredentials(
                userDto.getEmail(),
                userDto.getPassword(),
                passwordEncoder
        );

        if(user != null) {
            final String token = tokenProvider.create(user);
            final UserDto responseUserDTO = UserDto.builder()
                    .phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail())
                    .userId(user.getUserId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else{
            ResponseDto responseDTO = ResponseDto.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }


    }
}
