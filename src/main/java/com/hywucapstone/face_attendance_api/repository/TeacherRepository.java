package com.hywucapstone.face_attendance_api.repository;

import com.hywucapstone.face_attendance_api.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}