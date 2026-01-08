package com.hywucapstone.face_attendance_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String name;
    private String email;

    @Column(name = "phone_num")
    private String phoneNum;

    private String department;
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}