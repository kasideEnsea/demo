package com.company.demo.dao;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_and_group")
@Data
public class CourseAndGroupDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "course_id")
    private int courseId;
}
