package com.project.rocketlunch.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;

    @Column(nullable = true)
    private String message;

    @Column(nullable = false)
    private boolean initFlag;
}
