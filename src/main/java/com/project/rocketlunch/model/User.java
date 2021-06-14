package com.project.rocketlunch.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
