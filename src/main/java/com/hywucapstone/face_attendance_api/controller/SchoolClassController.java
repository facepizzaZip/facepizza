package com.hywucapstone.face_attendance_api.controller;

import com.hywucapstone.face_attendance_api.domain.SchoolClass;
import com.hywucapstone.face_attendance_api.repository.SchoolClassRepository;
import com.hywucapstone.face_attendance_api.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassRepository schoolClassRepository;
    private final EnrollmentRepository enrollmentRepository;

    // 1. 모든 수업 목록 조회 (관리자용)
    @GetMapping
    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    // 2. 수업 추가 (관리자용)
    @PostMapping
    public SchoolClass createClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    // 3. ✨ [수정됨] 학생의 시간표 조회 API
    // 원래는 "오늘 수업"만 줬지만, 이제는 "전체 시간표"를 줍니다.
    // 주소는 앱이랑 맞추기 위해 그대로 둡니다 (/today/...)
    @GetMapping("/today/{studentId}")
    public List<SchoolClass> getStudentClasses(@PathVariable Long studentId) {
        System.out.println("학생 ID " + studentId + "의 모든 수강 과목을 조회합니다.");
        
        // 요일 계산 로직 삭제! -> 무조건 전체 수업 리턴
        return enrollmentRepository.findAllClassesByStudentId(studentId);
    }
}