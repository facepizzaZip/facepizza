package com.hywucapstone.face_attendance_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "classes")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @Column(name = "class_name")
    private String className;

    private String schedule;

    @Column(name = "room_num")
    private String roomNum;

    // 선생님과 연결 (다대일 관계)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}