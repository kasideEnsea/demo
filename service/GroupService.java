package com.company.demo.service;

import com.company.demo.converter.GroupConverter;
import com.company.demo.converter.StudentConverter;
import com.company.demo.crud.CourseAndGroupCrud;
import com.company.demo.crud.GroupCrud;
import com.company.demo.crud.StudentCrud;
import com.company.demo.dao.CourseAndGroupDao;
import com.company.demo.dao.GroupDao;
import com.company.demo.dao.StudentDao;
import com.company.demo.entity.Group;
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
public class GroupService {
    private final StudentCrud studentCrud;
    private final GroupCrud groupCrud;
    private final CourseAndGroupCrud courseAndGroupCrud;
    private final StudentService studentService;

    public LinkedList<Group> getGroupsByCourseId(int courseId){
        List<CourseAndGroupDao> courseAndGroupDaos = courseAndGroupCrud.getByCourseId(courseId);
        LinkedList<Group> groups = new LinkedList<>();
        for (CourseAndGroupDao candg: courseAndGroupDaos
        ) {
            Group group = getGroupById(candg.getGroupId());
            groups.add(group);
        }
        return groups;
    }

    public Group getGroupById(int id){
        GroupDao groupDao = groupCrud.getById(id);
        return GroupConverter.daoToGroupSL(groupDao, studentService.getStudentsByGroupId(id));
    }


    public LinkedList<Group> getAll(){
        List<GroupDao> groupDaos = StreamSupport
                .stream(groupCrud.findAll().spliterator(), false)
                .collect(Collectors.toList());
        LinkedList<Group> groups = new LinkedList<>();
        for (GroupDao groupDao: groupDaos
        ) {
            List<StudentDao> studentDaos = studentCrud.getByGroupId(groupDao.getId());
            Group group = GroupConverter.daoToGroup(groupDao, studentDaos);
            groups.add(group);
        }
        return groups;
    }

    public Group addGroup(Group group){
        if (groupCrud.existsById(group.getId())) {
            throw new WebException("Group is already used", HttpStatus.BAD_REQUEST);
        }
        if (groupCrud.existsByName(group.getName())) {
            throw new WebException("Name is already used", HttpStatus.BAD_REQUEST);
        }
        GroupDao groupDao = GroupConverter.groupToDao(group);
        groupDao.setId(null);
        groupCrud.save(groupDao);
        return group;
    }

    public void deleteGroup(int id){
        if (!groupCrud.existsById(id)) {
            throw new WebException("No such group", HttpStatus.BAD_REQUEST);
        }
        groupCrud.deleteById(id);
    }

    public void addStudentInGroup(String studentName, int groupId){
        StudentDao studentDao = studentCrud.getByName(studentName);
        int studentId = studentDao.getId();
        Student student = studentService.getStudentById(studentId);
        GroupDao groupDao = groupCrud.getById(groupId);
        student.setGroupName(groupDao.getName());
        studentService.editStudent(student);
    }


}
