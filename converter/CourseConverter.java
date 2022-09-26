package com.company.demo.converter;

import com.company.demo.dao.CourseDao;
import com.company.demo.entity.Course;
import com.company.demo.entity.Group;
import com.company.demo.entity.User;

import java.util.LinkedList;

public class CourseConverter {
    public static Course daoToCourse(CourseDao dao, LinkedList<Group> groups){
        return (new Course(dao.getId(), dao.getName(), groups));
    }

    public static CourseDao courseToDao(Course course){
        CourseDao courseDao = new CourseDao();
        courseDao.setId(course.getId());
        courseDao.setName(course.getName());
        return courseDao;
    }
}
