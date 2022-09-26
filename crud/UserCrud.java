package com.company.demo.crud;

import com.company.demo.dao.UserDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCrud extends CrudRepository<UserDao, Integer>{
    boolean existsByLogin(String login);

    UserDao getByLogin(String login);

    UserDao getById(int id);

    List<UserDao> findAllByIdIn(List<Integer> ids);
}
