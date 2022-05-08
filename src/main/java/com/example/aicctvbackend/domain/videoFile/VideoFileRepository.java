package com.example.aicctvbackend.domain.videoFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {
    VideoFile findByFileId(Long fileId);
}
