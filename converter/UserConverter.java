package com.company.demo.converter;

import com.company.demo.dao.UserDao;
import com.company.demo.entity.User;
import com.company.demo.entity.Mode;

public class UserConverter {

    public static User daoToUser(UserDao dao){
        return new User(dao.getId(), dao.getLogin());
    }

    public static UserDao userToDao (User user, String password, String salt, String token){
        UserDao dao = new UserDao();
        dao.setId(user.getId());
        dao.setLogin(user.getLogin());
        dao.setPassToken(token);
        dao.setPassword(password);
        dao.setSalt(salt);
        return dao;
    }

}
