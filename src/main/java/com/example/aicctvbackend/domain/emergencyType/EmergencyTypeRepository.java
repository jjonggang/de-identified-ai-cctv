package com.example.aicctvbackend.domain.emergencyType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyTypeRepository extends JpaRepository<EmergencyType, Long> {

    EmergencyType findByTypeId(Long emergencyId);
}
