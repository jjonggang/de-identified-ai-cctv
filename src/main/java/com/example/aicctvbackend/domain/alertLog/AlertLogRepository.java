package com.example.aicctvbackend.domain.alertLog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {

    AlertLog findByLogId(Long logId);
}
