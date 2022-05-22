package com.example.aicctvbackend.controller.colabHost;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.colabHost.ColabHost;
import com.example.aicctvbackend.domain.user.User;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.colabHost.ColabHostRequestDto;
import com.example.aicctvbackend.dto.colabHost.ColabHostResponseDto;
import com.example.aicctvbackend.dto.response.ResponseDto;
import com.example.aicctvbackend.dto.user.UserDto;
import com.example.aicctvbackend.service.colabHost.ColabHostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ColabHostApiController {

    private final ColabHostService colabHostService;

    @PostMapping("/colab-host")
    public ResponseEntity<?> postHost(@RequestBody ColabHostRequestDto colabHostRequestDto){
        try{
            ColabHost colabHost = colabHostService.postNewHost(colabHostRequestDto.getHost(), colabHostRequestDto.getPortNumber(), colabHostRequestDto.getCameraId());
            ColabHostResponseDto dto = new ColabHostResponseDto(colabHost);
            return ResponseEntity.ok().body(dto);
        }catch(Exception e){
            //사용자 정보는 항상 하나이므로 리스트를 만들어야하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
            ResponseDto responseDTO = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @GetMapping("/colab-host")
    public ResponseEntity<?> getHost(@RequestParam("camera_id") Long cameraId){
        try{
            ColabHost colabHost = colabHostService.getHost(cameraId);
            ColabHostResponseDto dto = new ColabHostResponseDto(colabHost);
            return ResponseEntity.ok().body(dto);
        }catch(Exception e){
            //사용자 정보는 항상 하나이므로 리스트를 만들어야하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
            ResponseDto responseDTO = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }
}
