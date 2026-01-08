package com.hywucapstone.face_attendance_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    private String status; // PRESENT, LATE, ABSENT

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "check_in_time")
    private LocalTime checkInTime;
}