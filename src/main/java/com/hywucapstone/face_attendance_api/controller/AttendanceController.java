package com.hywucapstone.face_attendance_api.controller;

import com.hywucapstone.face_attendance_api.domain.Attendance;
import com.hywucapstone.face_attendance_api.domain.SchoolClass;
import com.hywucapstone.face_attendance_api.domain.Student;
import com.hywucapstone.face_attendance_api.repository.AttendanceRepository;
import com.hywucapstone.face_attendance_api.repository.SchoolClassRepository;
import com.hywucapstone.face_attendance_api.repository.StudentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    // 1. 출석 체크하기 (저장)
    // 프론트에서 { "studentId": 1, "classId": 3 } 이렇게 보내면 저장함
    @PostMapping
    public String checkIn(@RequestBody AttendanceRequest request) {
        Student student = studentRepository.findById(request.studentId).orElseThrow();
        SchoolClass schoolClass = schoolClassRepository.findById(request.classId).orElseThrow();

        // 이미 오늘 출석했는지 확인 (중복 방지)
        boolean alreadyCheckedIn = attendanceRepository.findAll().stream()
                .anyMatch(a -> a.getStudent().getStudentId().equals(request.studentId) &&
                               a.getSchoolClass().getClassId().equals(request.classId) &&
                               a.getAttendanceDate().isEqual(LocalDate.now()));

        if (alreadyCheckedIn) {
            return "이미 출석했습니다.";
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setSchoolClass(schoolClass);
        attendance.setStatus("PRESENT");
        attendance.setAttendanceDate(LocalDate.now()); // 오늘 날짜
        attendance.setCheckInTime(LocalTime.now());    // 현재 시간

        attendanceRepository.save(attendance);
        return "출석 완료!";
    }

    // 2. 오늘 출석한 수업 ID 목록 가져오기 (앱 켰을 때 초록색 표시용)
    @GetMapping("/today/{studentId}")
    public List<Long> getTodayAttendanceClassIds(@PathVariable Long studentId) {
        // 오늘 날짜에 해당 학생이 출석한 기록을 다 가져와서 -> 수업 ID만 리스트로 줌
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getStudent().getStudentId().equals(studentId))
                .filter(a -> a.getAttendanceDate().isEqual(LocalDate.now())) // 오늘 것만
                .map(a -> a.getSchoolClass().getClassId())
                .collect(Collectors.toList());
    }

    // (데이터 받을 틀)
    @Data
    static class AttendanceRequest {
        private Long studentId;
        private Long classId;
    }
    // -------------------------------------------------------
    // 👨‍🏫 [관리자용] 특정 수업(classId)의 오늘 출석한 학생 명단 조회
    // 주소: /api/attendance/admin/class/{classId}
    // -------------------------------------------------------
    @GetMapping("/admin/class/{classId}")
    public List<Student> getAttendedStudentsByClass(@PathVariable Long classId) {
        System.out.println("관리자가 " + classId + "번 수업의 오늘 출석 명단을 조회합니다.");

        return attendanceRepository.findAll().stream()
                // 1. 해당 수업(classId)의 기록만 필터링
                .filter(a -> a.getSchoolClass().getClassId().equals(classId))
                // 2. 오늘 날짜 기록만 필터링
                .filter(a -> a.getAttendanceDate().isEqual(LocalDate.now()))
                // 3. 출석 데이터(Attendance)에서 학생 정보(Student)만 쏙 뽑아내기
                .map(a -> a.getStudent())
                .collect(Collectors.toList());
    }
}