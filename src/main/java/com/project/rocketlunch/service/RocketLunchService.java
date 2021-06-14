package com.project.rocketlunch.service;

import com.google.common.hash.Hashing;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.repository.RocketLunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RocketLunchService {

    @Autowired
    private RocketLunchRepository repository;

    public void signUp(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        repository.save(user);
    }

    public boolean signIn(User user) {
        List<User> list = repository.findAllByEmailAndPassword(user.getEmail(), hashPassword(user.getPassword()));
        System.out.println(list.size());
        System.out.println(hashPassword(user.getPassword()));
        if (list.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    private String hashPassword(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
