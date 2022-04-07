package com.example.aicctvbackend.domain.alertLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {
    AlertLog findByLogId(Long logId);
    List<AlertLog> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);
}
