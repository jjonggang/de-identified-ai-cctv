package com.example.aicctvbackend.service.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.alertLog.AlertLogRepository;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.emergencyType.EmergencyType;
import com.example.aicctvbackend.domain.emergencyType.EmergencyTypeRepository;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.dto.response.ResponsePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public ResponsePageDto<AlertLogResponseDto> getAlertLogByUserId(Long userId, Pageable pageable) {
        List<AlertLog> alertLogList = alertLogRepository.findByUserIdOrderByCreatedDateDesc(userId, pageable);
        Long pageCount = alertLogRepository.CountByUserId(userId);
        log.info(String.valueOf(pageCount));
        Long totalCount = pageCount;

        Long listCount = (long)10;
        Long totalPage = (totalCount / listCount)-1;

        if (totalCount % listCount > 0) {
            totalPage++;
        }
        List<AlertLogResponseDto> dtos = alertLogList.stream()
                .map(log -> new AlertLogResponseDto(log))
                .collect(Collectors.toList());
        ResponsePageDto<AlertLogResponseDto> response = ResponsePageDto.<AlertLogResponseDto>builder()
                .lastPage((long)totalPage)
                .data(dtos)
                .build();
        return response;
    }
}
