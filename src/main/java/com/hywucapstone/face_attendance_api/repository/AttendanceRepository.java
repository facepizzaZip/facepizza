package com.hywucapstone.face_attendance_api.repository;

import com.hywucapstone.face_attendance_api.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}