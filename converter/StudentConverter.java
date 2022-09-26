package com.company.demo.converter;

import com.company.demo.dao.StudentDao;
import com.company.demo.entity.Student;

public class StudentConverter {
    public static Student daoToStudent(StudentDao dao, String groupName){
        return (new Student(dao.getId(), dao.getName(), dao.getPhotoURL(), groupName));
    }

    public static StudentDao studentToDao(Student student, int groupId){
        StudentDao studentDao = new StudentDao();
        studentDao.setId(student.getId());
        studentDao.setGroupId(groupId);
        studentDao.setName(student.getName());
        studentDao.setPhotoURL(student.getPhotoUrl());
        return studentDao;
    }
}
