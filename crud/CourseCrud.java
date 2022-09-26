package com.company.demo.crud;

import com.company.demo.dao.CourseDao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface CourseCrud extends CrudRepository<CourseDao, Integer>{
    List<CourseDao> getByTeacherId(int teacherId);
    CourseDao getById(int id);
    boolean existsByName(String name);
    CourseDao getByName(String name);
}
