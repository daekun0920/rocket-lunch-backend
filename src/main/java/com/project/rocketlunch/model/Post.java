package com.project.rocketlunch.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String food;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;
}
