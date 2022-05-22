package com.example.aicctvbackend.service.colabHost;

import com.example.aicctvbackend.domain.colabHost.ColabHost;
import com.example.aicctvbackend.domain.colabHost.ColabHostRepository;
import com.example.aicctvbackend.dto.colabHost.ColabHostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ColabHostService {

    private final ColabHostRepository colabHostRepository;

    public ColabHost postNewHost(String host, String portNumber, Long cameraId){
        ColabHost existHost = colabHostRepository.findByCameraId(cameraId);
        if(existHost == null){
            log.info("host not exists");
        }else{
            colabHostRepository.delete(existHost);
            log.info("delete success");
        }
        ColabHost newInHost = colabHostRepository.save(new ColabHost(host, portNumber, cameraId));
        return newInHost;
    }

    public ColabHost getHost(Long cameraId) {
        ColabHost colabHost = colabHostRepository.findByCameraId(cameraId);
        if(colabHost == null){
            log.info("host not exists");
            throw new RuntimeException("host not exist. 새로운 host와 port number를 등록하세요.");
        }
        return colabHost;
    }
}
