package com.company.demo.controller;

import com.company.demo.converter.StatementLineConverter;
import com.company.demo.dto.StringDto;
import com.company.demo.entity.Course;
import com.company.demo.entity.Group;
import com.company.demo.service.CourseService;
import com.company.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/")
    public List<Group> getAllGroups() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable int id){
        return groupService.getGroupById(id);
    }

    @PostMapping("/add")
    public Group addGroup (@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PostMapping("/del/{id}")
    public void deleteGroup (@PathVariable Integer id) {
        groupService.deleteGroup(id);
    }

    @PostMapping("/addstud/{groupid}/{student}")
    public void addStudentInGroup(@PathVariable int groupid, @RequestBody StringDto student){
        groupService.addStudentInGroup(student.getStr(), groupid);
    }

}
