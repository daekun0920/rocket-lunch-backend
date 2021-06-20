package com.project.rocketlunch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private String time;

    @Column(nullable = true)
    private boolean delFlag;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
