package com.company.demo.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Data
public class StudentDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "photoURL")
    private String photoURL;
    @Column(name = "group_id")
    private int groupId;

}
