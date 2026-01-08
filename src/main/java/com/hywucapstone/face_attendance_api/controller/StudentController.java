package com.hywucapstone.face_attendance_api.controller;

import com.hywucapstone.face_attendance_api.domain.Student;
import com.hywucapstone.face_attendance_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students") // 접속 주소: localhost:8080/api/students
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    // 1. 모든 학생 목록 조회 (GET)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. 학생 추가 (POST)
    // 프론트에서 JSON 데이터를 보내면 DB에 저장함
    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
}