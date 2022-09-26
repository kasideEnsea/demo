package com.company.demo.dto;

import com.company.demo.entity.Mode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SettingsDto {
    private Integer cardCount;
    private Integer mode;
}
