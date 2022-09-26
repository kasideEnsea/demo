package com.company.demo.controller;

import com.company.demo.dto.StatementDto;
import com.company.demo.entity.Course;
import com.company.demo.entity.Group;
import com.company.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public List<Course> getMyCourses() {
        List<Course> courseLinkedList = courseService.getCourses();
        System.out.println(courseLinkedList);
        return courseLinkedList;
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id){
        System.out.println(courseService.getCourseById(id));
        return courseService.getCourseById(id);
    }

    @GetMapping("/statement/{group}/{course}")
    public StatementDto getStatement(@PathVariable int group, @PathVariable int course){
        return courseService.getStatementByCourseAndGroup(course, group);
    }

    @PostMapping("/del/{id}")
    public void deleteCourse (@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/delgroup/{course}/{group}")
    public void deleteGroupFromCourse (@PathVariable int course, @PathVariable int group) {
        courseService.deleteGroupFromCourse(group, course);
    }

    @PostMapping("/add")
    public Course addCourse (@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PostMapping("/addgroup/{course}")
    public void addGroupInCourse(@PathVariable int course, @RequestBody Group group){
        courseService.addGroupInCourse(group.getName(), course);
    }

}
