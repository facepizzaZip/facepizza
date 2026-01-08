package com.hywucapstone.face_attendance_api.repository;

import com.hywucapstone.face_attendance_api.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// DB에서 학생 데이터를 가져오는 도구 (JPA가 자동으로 만들어줌)
public interface StudentRepository extends JpaRepository<Student, Long> {
}