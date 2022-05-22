package com.example.aicctvbackend.domain.colabHost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColabHostRepository extends JpaRepository<ColabHost, Long> {

    ColabHost findByCameraId(Long cameraId);
}
