package com.company.demo.dto;

import com.company.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUser {
    private User user;
    private String tokenData;
}
