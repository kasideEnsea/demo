package com.company.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDataDto {
    private String login;
    private String password;
}
