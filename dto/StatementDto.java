package com.company.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class StatementDto {
    private LinkedList<String> fullNames;
    private boolean[][] isPresent;
    private LinkedList<LocalDateTime> dates;
}
