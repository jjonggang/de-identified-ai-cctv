package com.example.aicctvbackend.controller.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.dto.response.ResponsePageDto;
import com.example.aicctvbackend.service.alertLog.AlertLogService;
import com.example.aicctvbackend.socket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j // log 보여주기 lof.info()
@RequiredArgsConstructor // private final을 사용하기 위한
@RestController // Rest Api Controller
@RequestMapping(value = "/api/v1") //
public class AlertLogApiController {
    private final AlertLogService alertLogService;
    private final WebSocketHandler webSocketHandler;

    @PostMapping("/alert-log")
    public ResponseEntity<?> postAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto){
        AlertLog inputAlert = alertLogService.postAlertLog(alertLogRequestDto);
//        alertLogService.sendSms(inputAlert);
        webSocketHandler.sendMessageToAll(new TextMessage(inputAlert.getLogId().toString())); // 소켓메시지로 문제상황 알리기
        return ResponseEntity.ok().body(inputAlert);
    }

    // Get Mapping
    @GetMapping("/alert-log/{logId}")
    public ResponseEntity<?> getAlertLogById(@PathVariable Long logId) {
        AlertLog tempLog = alertLogService.getAlertLogById(logId);
        AlertLogResponseDto dto = new AlertLogResponseDto(tempLog);
        return ResponseEntity.ok().body(dto);
    }

    // 유저 관리 반에 대한 alert log 가져오기
    @GetMapping("/alert-log/list")
    public ResponseEntity<?> getAlertLogByUserId(@AuthenticationPrincipal String strUserId, @PageableDefault(page = 0, size=10) Pageable pageable){
        Long userId = Long.parseLong(strUserId);
        ResponsePageDto<AlertLogResponseDto> response = alertLogService.getAlertLogList(userId, pageable);

        return ResponseEntity.ok().body(response);
    }

}
