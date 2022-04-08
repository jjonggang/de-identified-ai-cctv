package com.example.aicctvbackend.domain.alertLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {
    AlertLog findByLogId(Long logId);
    List<AlertLog> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM alert_log WHERE user_id = ?1", nativeQuery = true)
    Long CountByUserId(Long userId);
}
