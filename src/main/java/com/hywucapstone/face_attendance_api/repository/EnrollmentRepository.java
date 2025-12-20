package com.hywucapstone.face_attendance_api.repository;

import com.hywucapstone.face_attendance_api.domain.Enrollment;
import com.hywucapstone.face_attendance_api.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // 기존: 요일별 조회
    // @Query("SELECT e.schoolClass FROM Enrollment e WHERE e.student.studentId = :studentId AND e.schoolClass.schedule LIKE %:day%")
    // List<SchoolClass> findTodayClasses(@Param("studentId") Long studentId, @Param("day") String day);

    // ✅ [추가] 요일 상관없이 해당 학생의 모든 수업 조회
    @Query("SELECT e.schoolClass FROM Enrollment e WHERE e.student.studentId = :studentId")
    List<SchoolClass> findAllClassesByStudentId(@Param("studentId") Long studentId);
}