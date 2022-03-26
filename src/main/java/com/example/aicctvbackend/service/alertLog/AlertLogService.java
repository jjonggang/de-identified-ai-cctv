package com.example.aicctvbackend.service.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.alertLog.AlertLogRepository;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertLogService {
    private final AlertLogRepository alertLogRepository;

    public AlertLog getAlertLogById(Long logId){
        AlertLog alertLog = alertLogRepository.findByLogId(logId);
        return alertLog;
    }

}
