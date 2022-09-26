package com.company.demo.service;

import com.company.demo.converter.UserConverter;
import com.company.demo.dao.UserDao;
import com.company.demo.dto.AuthUser;
import com.company.demo.entity.User;
import com.company.demo.exception.WebException;
import com.company.demo.security.HashUtills;
import com.company.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.company.demo.crud.UserCrud;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserCrud userCrud;

    public AuthUser authorizeUser(String login, String password) {
        try {
            UserDao userDao = authUser(login, password);
            int userId = userDao.getId();
            String tokenLogin = String.format("%d %s", userId, login);
            String token = jwtTokenProvider.createToken(tokenLogin);
            userDao.setPassToken(JwtTokenProvider.BEARER + token);
            userCrud.save(userDao);
            return new AuthUser(UserConverter.daoToUser(userDao), JwtTokenProvider.BEARER + token);
        } catch (Exception e) {
            log.error("Error during authorization", e);
            throw e;
        }
    }

    private UserDao authUser(String login, String password) {
        if (!userCrud.existsByLogin(login)) {
            throw new WebException("Login doesn't exist", HttpStatus.UNAUTHORIZED);
        }
        UserDao dao = userCrud.getByLogin(login);
        if(!Objects.equals(HashUtills.hashPassword(password, dao.getSalt()), dao.getPassword())){
            throw new WebException("Wrong password", HttpStatus.FORBIDDEN);
        }
        return dao;
    }
}
