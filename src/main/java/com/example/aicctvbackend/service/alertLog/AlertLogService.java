package com.example.aicctvbackend.service.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.alertLog.AlertLogRepository;
import com.example.aicctvbackend.domain.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.classroom.Classroom;
import com.example.aicctvbackend.domain.classroom.ClassroomRepository;
import com.example.aicctvbackend.domain.emergencyType.EmergencyTypeRepository;
import com.example.aicctvbackend.domain.managerOfClassroom.ManagerOfClassroomRepository;
import com.example.aicctvbackend.domain.videoFile.VideoFileRepository;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.dto.response.ResponsePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertLogService {
    private final AlertLogRepository alertLogRepository;
    private final CaptureFileRepository captureFileRepository;
    private final EmergencyTypeRepository emergencyTypeRepository;
    private final VideoFileRepository videoFileRepository;
    private final ClassroomRepository classroomRepository;
    private final ManagerOfClassroomRepository managerOfClassroomRepository;

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    public AlertLog getAlertLogById(Long logId){
        AlertLog alertLog = alertLogRepository.findByLogId(logId);
        return alertLog;
    }

    public AlertLog postAlertLog(AlertLogRequestDto alertLogRequestDto) {
        AlertLog alertLog = AlertLog.builder()
                .emergencyType(emergencyTypeRepository.findByTypeId(alertLogRequestDto.getEmergencyId()))
                .captureFile(captureFileRepository.findByFileId(alertLogRequestDto.getCaptureFileId()))
                .videoFile(videoFileRepository.findByFileId(alertLogRequestDto.getVideoFileId()))
                .classroom(classroomRepository.findByClassroomId(alertLogRequestDto.getClassroomId()))
                .numOfParticipant(alertLogRequestDto.getNumOfParticipant())
                .bottomRightx(alertLogRequestDto.getBottomRightx())
                .bottomRighty(alertLogRequestDto.getBottomRighty())
                .upperLeftx(alertLogRequestDto.getUpperLeftx())
                .upperLefty(alertLogRequestDto.getUpperLefty())
                .build();
        alertLogRepository.save(alertLog);
        return alertLog;
    }

    public ResponsePageDto<AlertLogResponseDto> getAlertLogList(Long userId, Pageable pageable){
        List<Long> classroomIdList = managerOfClassroomRepository.findClassroomIdByUserId(userId);
        List<AlertLog> alertLogList = new ArrayList<>();
        if (classroomIdList.size() == 0){
            throw new IllegalStateException("사용자가 관리하는 반이 없습니다. ");
        } else if(classroomIdList.size() == 1){
            alertLogList = alertLogRepository.findByClassroomIdOrderByCreatedDateDesc(classroomIdList.get(0), pageable);
        } else{
            alertLogList = alertLogRepository.findByClassroomIdListOrderByCreatedDateDesc(classroomIdList, pageable);
        }

        int totalCount = alertLogList.size();
        int listCount = 10;
        int totalPageCount = (totalCount / listCount)-1;

        if (totalCount % listCount > 0) {
            totalPageCount++;
        }
        List<AlertLogResponseDto> dtos = alertLogList.stream()
                .map(log -> new AlertLogResponseDto(log))
                .collect(Collectors.toList());
        ResponsePageDto<AlertLogResponseDto> response = ResponsePageDto.<AlertLogResponseDto>builder()
                .lastPage((long)totalPageCount)
                .data(dtos)
                .build();
        return response;

    }


//    public ResponsePageDto<AlertLogResponseDto> getAlertLogByUserId(Long userId, Pageable pageable) {
//        List<AlertLog> alertLogList = alertLogRepository.findByUserIdOrderByCreatedDateDesc(userId, pageable);
//        Long pageCount = alertLogRepository.CountByUserId(userId);
//        log.info(String.valueOf(pageCount));
//        Long totalCount = pageCount;
//
//        Long listCount = (long)10;
//        Long totalPage = (totalCount / listCount)-1;
//
//        if (totalCount % listCount > 0) {
//            totalPage++;
//        }
//        List<AlertLogResponseDto> dtos = alertLogList.stream()
//                .map(log -> new AlertLogResponseDto(log))
//                .collect(Collectors.toList());
//        ResponsePageDto<AlertLogResponseDto> response = ResponsePageDto.<AlertLogResponseDto>builder()
//                .lastPage((long)totalPage)
//                .data(dtos)
//                .build();
//        return response;
//    }

    public void sendSms(AlertLog inputAlert) {

        Message coolsms = new Message(apiKey, apiSecret);
        HashMap<String, String> params = new HashMap<String, String>();

//        inputAlert

        params.put("to", "");
        params.put("from", "01050596837");
        params.put("type", "SMS");
        params.put("text", "문제상황 발생");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

}
