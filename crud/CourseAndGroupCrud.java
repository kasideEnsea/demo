package com.company.demo.crud;

import com.company.demo.dao.CourseAndGroupDao;
import com.company.demo.dao.GroupDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseAndGroupCrud extends CrudRepository<CourseAndGroupDao, Integer> {
    List<CourseAndGroupDao> getByCourseId(int courseId);
    CourseAndGroupDao getByCourseIdAndGroupId(int courseId, int groupId);
    boolean existsByCourseIdAndGroupId(int courseId, int groupId);
}
