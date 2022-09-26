package com.company.demo.converter;

import com.company.demo.dao.StatementLineDao;
import com.company.demo.entity.Course;
import com.company.demo.entity.StatementLine;
import com.company.demo.entity.Student;
import com.company.demo.entity.User;

import java.util.LinkedList;
import java.util.List;

public class StatementLineConverter {
    public static StatementLine daoToStatementLine(StatementLineDao dao, Student student, Course course){
        return new StatementLine(dao.getId(), student, dao.isPresent(), course, dao.getDate());
    }

    public static StatementLineDao statementLineToDao(StatementLine statementLine){
        StatementLineDao statementLineDao = new StatementLineDao();
        statementLineDao.setId(statementLine.getId());
        statementLineDao.setStudentId(statementLine.getStudent().getId());
        statementLineDao.setCourseId(statementLine.getCourse().getId());
        statementLineDao.setPresent(statementLine.isPresent());
        statementLineDao.setDate(statementLine.getDate());
        return statementLineDao;
    }
}
