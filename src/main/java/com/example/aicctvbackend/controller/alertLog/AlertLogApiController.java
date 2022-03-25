package com.example.aicctvbackend.controller.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.service.alertLog.AlertLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class AlertLogApiController {
    private final AlertLogService alertLogService;

    @GetMapping("/alert-log/get/{logId}")
    public ResponseEntity<?> getAlertLogById(@PathVariable Long logId) {
        AlertLog tempLog = alertLogService.getAlertLogById(logId);
        AlertLogResponseDto dto = new AlertLogResponseDto(tempLog);
        return ResponseEntity.ok().body(dto);
    }

}
