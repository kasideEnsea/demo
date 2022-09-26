package com.company.demo.service;

import com.company.demo.converter.StudentConverter;
import com.company.demo.crud.GroupCrud;
import com.company.demo.crud.StudentCrud;
import com.company.demo.dao.StudentDao;
import com.company.demo.entity.Student;
import com.company.demo.exception.WebException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentCrud studentCrud;
    private final GroupCrud groupCrud;

    public LinkedList<Student> getStudentsByGroupId(int groupId){
        List<StudentDao> studentDaos = studentCrud.getByGroupId(groupId);
        LinkedList<Student> students = new LinkedList<>();
        for (StudentDao dao: studentDaos
             ) {
            Student student = getStudentById(dao.getId());
            students.add(student);
        }
        return students;
    }

    public Student getStudentById(int id){
        StudentDao studentDao = studentCrud.getById(id);
        int groupId = studentDao.getId();
        if (!groupCrud.existsById(groupId)) {
            throw new WebException("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
        String groupName = groupCrud.getById(groupId).getName();
        return StudentConverter.daoToStudent(studentDao, groupName);
    }

    public LinkedList<Student> getAll(){
        LinkedList<Student> students = new LinkedList<>();
        List<StudentDao> studentDaos = StreamSupport
                .stream(studentCrud.findAll().spliterator(), false)
                .collect(Collectors.toList());
        for (StudentDao dao: studentDaos
        ) {
            Student student = getStudentById(dao.getId());
            students.add(student);
        }
        return students;
    }

    public Student addStudent(Student student){
        if (studentCrud.existsByName(student.getName())) {
            throw new WebException("Name is already used", HttpStatus.BAD_REQUEST);
        }
        if (!groupCrud.existsByName(student.getGroupName())) {
            throw new WebException("No such group", HttpStatus.BAD_REQUEST);
        }
        int groupId = groupCrud.getByName(student.getGroupName()).getId();
        StudentDao studentDao = StudentConverter.studentToDao(student, groupId);
        studentDao.setId(null);
        studentCrud.save(studentDao);
        return student;
    }

    public Student editStudent(Student student){
        if (!studentCrud.existsById(student.getId())) {
            throw new WebException("No such student", HttpStatus.BAD_REQUEST);
        }
        if (!groupCrud.existsByName(student.getGroupName())) {
            throw new WebException("No such group", HttpStatus.BAD_REQUEST);
        }
        int groupId = groupCrud.getByName(student.getGroupName()).getId();
        StudentDao studentDao = StudentConverter.studentToDao(student, groupId);
        studentCrud.save(studentDao);
        return student;
    }

    public void deleteStudent(int id){
        if (!studentCrud.existsById(id)) {
            throw new WebException("No such student", HttpStatus.BAD_REQUEST);
        }
        studentCrud.deleteById(id);
    }
}
