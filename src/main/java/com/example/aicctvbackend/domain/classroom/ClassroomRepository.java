package com.example.aicctvbackend.domain.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    Classroom findByClassroomId(Long classroomId);
}
