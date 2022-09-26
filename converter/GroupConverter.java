package com.company.demo.converter;

import com.company.demo.dao.CourseDao;
import com.company.demo.dao.GroupDao;
import com.company.demo.dao.StudentDao;
import com.company.demo.entity.Course;
import com.company.demo.entity.Group;
import com.company.demo.entity.Student;

import java.util.LinkedList;
import java.util.List;

public class GroupConverter {
    public static Group daoToGroup (GroupDao groupDao, List<StudentDao> studentDaoList){
        LinkedList<Student> students = new LinkedList<>();
        for (StudentDao dao: studentDaoList
             ) {
            Student student = StudentConverter.daoToStudent(dao, groupDao.getName());
            students.add(student);
        }
        return new Group(groupDao.getId(), groupDao.getName(), students);
    }

    public static Group daoToGroupSL (GroupDao groupDao, LinkedList<Student> students){
        return new Group(groupDao.getId(), groupDao.getName(), students);
    }

    public static GroupDao groupToDao (Group group){
        GroupDao groupDao = new GroupDao();
        groupDao.setId(group.getId());
        groupDao.setName(group.getName());
        return groupDao;
    }

}
