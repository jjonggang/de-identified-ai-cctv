package com.example.aicctvbackend.service.alertLog;

import com.example.aicctvbackend.domain.alertLog.AlertLog;
import com.example.aicctvbackend.domain.alertLog.AlertLogRepository;
import com.example.aicctvbackend.domain.amazonS3.captureFile.CaptureFileRepository;
import com.example.aicctvbackend.domain.camera.Camera;
import com.example.aicctvbackend.domain.camera.CameraRepository;
import com.example.aicctvbackend.domain.classroom.ClassroomRepository;
import com.example.aicctvbackend.domain.emergencyType.EmergencyTypeRepository;
import com.example.aicctvbackend.domain.managerOfClassroom.ManagerOfClassroomRepository;
import com.example.aicctvbackend.domain.amazonS3.videoFile.VideoFileRepository;
import com.example.aicctvbackend.domain.user.User;
import com.example.aicctvbackend.domain.user.UserRepository;
import com.example.aicctvbackend.dto.alertLog.AlertLogRequestDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogResponseDto;
import com.example.aicctvbackend.dto.alertLog.AlertLogUpdateRequestDto;
import com.example.aicctvbackend.dto.response.ResponsePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.velocity.runtime.Runtime;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final UserRepository userRepository;

    private final CameraRepository cameraRepository;

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
                .cameraId(alertLogRequestDto.getCameraId())
                .createdDate(LocalDateTime.now())
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
        int totalPageCount = (totalCount / listCount);

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


    public void sendSms(AlertLog inputAlert) {

        Message coolsms = new Message(apiKey, apiSecret);
        HashMap<String, String> params = new HashMap<String, String>();

        Long cameraId = inputAlert.getCameraId();
        Camera camera = cameraRepository.findByCameraId(cameraId);
        List<Long> userIds = managerOfClassroomRepository.findUserIdByClassroomId(camera.getClassroomId());
        List<User> users = userRepository.findAllById(userIds);
        if(users == null){
            throw new RuntimeException("카메라가 설치된 학급에 대한 관리자가 지정돼있지 않습니다. ");
        }
        for (User user : users) {
            params.put("to", user.getPhoneNumber());
            params.put("from", "01050596837");
            params.put("type", "LMS");
            params.put("text", inputAlert.getEmergencyType().getTypeNameKor() + "발생!" + "\n\n" + "학급: " + inputAlert.getClassroom().getClassroomName()
                    + "\n발생 시각: " + inputAlert.getCreatedDate() + "\n인원: " + inputAlert.getNumOfParticipant() + "\n위험상황 캡쳐 파일: " + inputAlert.getCaptureFile().getFilePath());

            params.put("app_version", "test app 1.2");
            log.info(String.valueOf(params));
            try {
                JSONObject obj = (JSONObject) coolsms.send(params);
                System.out.println(obj.toString());
            } catch (CoolsmsException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCode());
            }
        }

//        inputAlert


    }

    public AlertLog updateAlertLog(AlertLogUpdateRequestDto alertLogUpdateRequestDto) {
        AlertLog alertLog = alertLogRepository.findByLogId(alertLogUpdateRequestDto.getLogId());
        AlertLog newAlertLog = AlertLog.builder()
                .logId(alertLog.getLogId())
                .emergencyType(alertLog.getEmergencyType())
                .captureFile(alertLog.getCaptureFile())
                .videoFile(videoFileRepository.findByFileId(alertLogUpdateRequestDto.getVideoId()))
                .classroom(classroomRepository.findByClassroomId(alertLog.getClassroom().getClassroomId()))
                .numOfParticipant(alertLog.getNumOfParticipant())
                .bottomRightx(alertLog.getBottomRightx())
                .bottomRighty(alertLog.getBottomRighty())
                .upperLeftx(alertLog.getUpperLeftx())
                .upperLefty(alertLog.getUpperLefty())
                .cameraId(alertLog.getCameraId())
                .createdDate(alertLog.getCreatedDate())
                .build();
        log.info("update");
        return alertLogRepository.save(newAlertLog);
    }
}
