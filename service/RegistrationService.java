package com.company.demo.service;

import com.company.demo.crud.UserCrud;
import com.company.demo.dao.UserDao;
import com.company.demo.exception.WebException;
import com.company.demo.security.HashUtills;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserCrud userCrud;

    public void register(String login, String password) {
        if (userCrud.existsByLogin(login)) {
            throw new WebException("Login is already used", HttpStatus.BAD_REQUEST);
        }
        String salt = HashUtills.generateSalt(10);
        password = HashUtills.hashPassword(password, salt);
        UserDao userDao = new UserDao();
        userDao.setLogin(login);
        userDao.setPassword(password);
        userDao.setSalt(salt);
        userCrud.save(userDao);
    }
}
