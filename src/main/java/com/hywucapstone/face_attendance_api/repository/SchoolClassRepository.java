package com.hywucapstone.face_attendance_api.repository;

import com.hywucapstone.face_attendance_api.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}