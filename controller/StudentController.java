package com.company.demo.controller;

import com.company.demo.entity.Student;
import com.company.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/add")
    public Student addStudent (@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/del/{id}")
    public void deleteStudent (@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/edit")
    public Student editStudent (@Valid @RequestBody Student student) {
        return studentService.editStudent(student);
    }
}
