package com.example.aicctvbackend.domain.managerOfClassroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerOfClassroomRepository extends JpaRepository<ManagerOfClassroom, Long> {

    @Query(nativeQuery = true, value = "SELECT classroom_id FROM manager_of_classroom WHERE user_id=?1")
    List<Long> findClassroomIdByUserId(Long userId);
}
