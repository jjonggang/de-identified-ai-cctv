package com.example.aicctvbackend.domain.managerOfClassroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerOfClassroomRepository extends JpaRepository<ManagerOfClassroom, Long> {


    List<Long> findClassroomIdByUserId(Long userId);
}
