package com.company.demo.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "studcheck_user")
@Data
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "pass_token")
    private String passToken;
    @Column(name = "salt")
    private String salt;
}
