package com.company.demo.controller;

import com.company.demo.dto.AuthUser;
import com.company.demo.entity.User;
import com.company.demo.dto.LoginDataDto;
import com.company.demo.exception.WebException;
import com.company.demo.service.AuthorizationService;
import com.company.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final AuthorizationService authorizationService;

    @PostMapping("/")
    public AuthUser register(@Valid @RequestBody LoginDataDto dto) {
        registrationService.register(dto.getLogin(), dto.getPassword());
        return authorizationService.authorizeUser(dto.getLogin(), dto.getPassword());
    }
}

