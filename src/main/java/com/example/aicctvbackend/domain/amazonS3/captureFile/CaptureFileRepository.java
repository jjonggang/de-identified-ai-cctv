package com.example.aicctvbackend.domain.amazonS3.captureFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptureFileRepository extends JpaRepository<CaptureFile, Long> {
    CaptureFile findByFileId(Long fileId);
}
