package com.example.aicctvbackend.domain.alertLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {
    AlertLog findByLogId(Long logId);

    @Query(nativeQuery = true, value = "SELECT * FROM alert_log WHERE classroom_id=?1 ORDER BY created_date DESC")
    List<AlertLog> findByClassroomIdOrderByCreatedDateDesc(Long classroomId, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM alert_log WHERE classroom_id IN (:classroomIdList) ORDER BY created_date DESC")
    List<AlertLog> findByClassroomIdListOrderByCreatedDateDesc(@Param("classroomIdList") List<Long> classroomIdList, Pageable pageable);


//    List<AlertLog> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);
//    @Query(value = "SELECT COUNT(*) FROM alert_log WHERE user_id = ?1", nativeQuery = true)
//    Long CountByUserId(Long userId);
}
