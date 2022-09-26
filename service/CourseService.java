package com.company.demo.service;

import com.company.demo.converter.*;
import com.company.demo.crud.*;
import com.company.demo.dao.*;
import com.company.demo.dto.StatementDto;
import com.company.demo.entity.*;
import com.company.demo.exception.WebException;
import com.company.demo.security.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseCrud courseCrud;
    private final CourseAndGroupCrud courseAndGroupCrud;
    private final GroupCrud groupCrud;
    private final StatementLineCrud statementLineCrud;
    private final StudentCrud studentCrud;

    private final GroupService groupService;
    private final StudentService studentService;

    public List<Course> getCourses() {
        List<CourseDao> daoList = courseCrud.getByTeacherId(CurrentUserService.getUserId());
        LinkedList<Course> courses = new LinkedList<>();

        for (CourseDao cd: daoList
        ) {
            Course course = getCourseById(cd.getId());
            courses.addLast(course);
        }
        return courses;
    }

    public Course getCourseById(int id) {
        if (!courseCrud.existsById(id)) {
            throw new WebException("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
        CourseDao dao = courseCrud.getById(id);
        if (dao.getTeacherId() != CurrentUserService.getUserId()) {
            throw new WebException("Foreign course", HttpStatus.FORBIDDEN);
        }
        LinkedList<Group> groups = groupService.getGroupsByCourseId(id);
        return CourseConverter.daoToCourse(dao, groups);
    }

    public Course addCourse(Course course){
        if (courseCrud.existsById(course.getId())) {
            throw new WebException("Course is already used", HttpStatus.BAD_REQUEST);
        }
        if (courseCrud.existsByName(course.getName())) {
            throw new WebException("Name is already used", HttpStatus.BAD_REQUEST);
        }
        CourseDao courseDao = CourseConverter.courseToDao(course);
        courseDao.setTeacherId(CurrentUserService.getUserId());
        courseDao.setId(null);
        courseCrud.save(courseDao);
        return course;
    }

    public void deleteCourse(int id){
        if (!courseCrud.existsById(id)) {
            throw new WebException("Course doesn't exist", HttpStatus.BAD_REQUEST);
        }
        courseCrud.deleteById(id);
    }

    public void addGroupInCourse(String groupName, int courseId){
        if(!groupCrud.existsByName(groupName)){
            throw new WebException("Group not found", HttpStatus.NOT_FOUND);
        }
        GroupDao groupDao = groupCrud.getByName(groupName);
        if(!courseCrud.existsById(courseId)){
            throw new WebException("Course not found", HttpStatus.NOT_FOUND);
        }
        if (courseAndGroupCrud.existsByCourseIdAndGroupId(groupDao.getId(), courseId)){
            throw new WebException("Already in course", HttpStatus.BAD_REQUEST);
        }
        CourseAndGroupDao courseAndGroupDao = new CourseAndGroupDao();
        courseAndGroupDao.setId(null);
        courseAndGroupDao.setCourseId(courseId);
        courseAndGroupDao.setGroupId(groupDao.getId());
        courseAndGroupCrud.save(courseAndGroupDao);
    }

    public void deleteGroupFromCourse(int groupId, int courseId){
        if (!courseAndGroupCrud.existsByCourseIdAndGroupId(groupId, courseId)){
            throw new WebException("Group not found", HttpStatus.BAD_REQUEST);
        }
        CourseAndGroupDao courseAndGroupDao = courseAndGroupCrud.getByCourseIdAndGroupId(courseId, groupId);
        courseAndGroupCrud.deleteById(courseAndGroupDao.getId());
    }

    public StatementDto getStatementByCourseAndGroup(int courseId, int groupId){
        LinkedList<StatementLine> statementLines = new LinkedList<>();
        if(!courseCrud.existsById(courseId)){
            throw new WebException("Course not found", HttpStatus.BAD_REQUEST);
        }
        if(!groupCrud.existsById(groupId)){
            throw new WebException("Group not found", HttpStatus.BAD_REQUEST);
        }
        if (!courseAndGroupCrud.existsByCourseIdAndGroupId(groupId, courseId)){
            throw new WebException("Group not found", HttpStatus.BAD_REQUEST);
        }
        List<StatementLineDao> courseStatementLines = statementLineCrud.getByCourseId(courseId);
        for (StatementLineDao dao: courseStatementLines
             ) {
            StudentDao studentDao = studentCrud.getById(dao.getStudentId());
            if (studentDao.getGroupId()==groupId){
                Course course = getCourseById(courseId);
                Student student = studentService.getStudentById(dao.getStudentId());
                statementLines.add(StatementLineConverter.daoToStatementLine(dao, student, course));
            }
        }
        LinkedList<String> names = new LinkedList<>();
        LinkedList<LocalDateTime> dates = new LinkedList<>();
        for (StatementLine sl: statementLines
             ) {
            if(!names.contains(sl.getStudent().getName())){
                names.add(sl.getStudent().getName());
            }
            if(!dates.contains(sl.getDate())){
                dates.add(sl.getDate());
            }
        }
        names.sort(Comparator.naturalOrder());
        dates.sort(Comparator.naturalOrder());

        boolean[][] isPresent = new boolean[names.size()][dates.size()];
        for (int i=0; i<names.size(); i++){
            for (int j=0; j<dates.size(); j++){
                for (StatementLine sl: statementLines
                     ) {
                    if(sl.getStudent().getName().equals(names.get(i))
                    && sl.getDate().equals(dates.get(j))){
                        isPresent[i][j] = sl.isPresent();
                    }
                }
            }
        }
        return new StatementDto(names, isPresent, dates);
    }


}
