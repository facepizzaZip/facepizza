package com.hywucapstone.face_attendance_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    @Column(name = "student_num")
    private String studentNum;

    private String department;

    @Column(name = "phone_num")
    private String phoneNum;

    private String email;

    @Column(name = "face_file_path")
    private String faceFilePath;

    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}