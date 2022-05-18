package com.example.aicctvbackend.domain.amazonS3.videoFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {
    VideoFile findByFileId(Long fileId);
}
