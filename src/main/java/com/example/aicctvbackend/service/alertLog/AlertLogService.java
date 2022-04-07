package com.example.aicctvbackend.service.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.alertLog.AlertLogRepository;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.example.aicctvbackend.domain.emergencyType.EmergencyTypeRepository;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertLogService {
    private final AlertLogRepository alertLogRepository;
    private final CaptureFileRepository captureFileRepository;
    private final EmergencyTypeRepository emergencyTypeRepository;

    public AlertLog getAlertLogById(Long logId){
        AlertLog alertLog = alertLogRepository.findByLogId(logId);
        return alertLog;
    }

    public AlertLog postAlertLog(AlertLogRequestDto alertLogRequestDto) {
        AlertLog alertLog = new AlertLog();
        alertLog.setCaptureFile(captureFileRepository.findByFileId(alertLogRequestDto.getCaptureFileId()));
        alertLog.setEmergencyType(emergencyTypeRepository.findByTypeId(alertLogRequestDto.getEmergencyId()));
        alertLog.setUpperLeftx(alertLogRequestDto.getUpperLeftx());
        alertLog.setUpperLefty(alertLogRequestDto.getUpperLefty());
        alertLog.setBottomRightx(alertLogRequestDto.getBottomRightx());
        alertLog.setBottomRighty(alertLogRequestDto.getBottomRighty());
        alertLogRepository.save(alertLog);
        return alertLog;
    }

    public List<AlertLog> getAlertLogByUserId(Long userId, Pageable pageable) {
        List<AlertLog> alertLogList = alertLogRepository.findByUserIdOrderByCreatedDateDesc(userId, pageable);
        return alertLogList;
    }
}
