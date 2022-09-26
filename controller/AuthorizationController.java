package com.company.demo.controller;

import com.company.demo.dto.AuthUser;
import com.company.demo.entity.User;
import com.company.demo.dto.LoginDataDto;
import com.company.demo.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/")
    public AuthUser authUser(@Valid @RequestBody LoginDataDto loginOptionsDto) {
        return authorizationService.authorizeUser(loginOptionsDto.getLogin(), loginOptionsDto.getPassword());
    }
}
