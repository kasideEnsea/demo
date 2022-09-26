package com.company.demo.crud;
import com.company.demo.dao.StudentDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentCrud extends CrudRepository<StudentDao, Integer> {
    StudentDao getById(int id);
    List<StudentDao> getByGroupId(int groupId);
    boolean existsByName(String name);
    StudentDao getByName(String name);
    boolean existsByPhotoURL(String photoURL);
}
