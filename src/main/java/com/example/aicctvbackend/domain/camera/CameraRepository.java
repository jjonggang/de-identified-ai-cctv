package com.example.aicctvbackend.domain.camera;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    Camera findByCameraId(Long cameraId);
}
