package com.company.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class StatementLine {
    private int id;
    private Student student;
    private boolean isPresent;
    private Course course;
    private LocalDateTime date;
}
