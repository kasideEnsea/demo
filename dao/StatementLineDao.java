package com.company.demo.dao;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "statement_line")
@Data
public class StatementLineDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "is_present")
    private boolean isPresent;
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "date")
    private LocalDateTime date;

}
