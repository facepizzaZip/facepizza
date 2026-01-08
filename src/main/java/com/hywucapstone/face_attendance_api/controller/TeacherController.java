package com.hywucapstone.face_attendance_api.controller;

import com.hywucapstone.face_attendance_api.domain.Teacher;
import com.hywucapstone.face_attendance_api.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @PostMapping
    public Teacher registerTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}