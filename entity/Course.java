package com.company.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private LinkedList<Group> groups;
}
