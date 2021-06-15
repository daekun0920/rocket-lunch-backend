package com.project.rocketlunch.service;

import com.google.common.hash.Hashing;
import com.project.rocketlunch.model.Post;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.repository.PostRepository;
import com.project.rocketlunch.repository.UserRepository;
import com.project.rocketlunch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
public class RocketLunchService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public void signUp(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getId());
    }

    public boolean signIn(User user) {
        List<User> list = userRepository.findAllByEmailAndPassword(user.getEmail(), hashPassword(user.getPassword()));
        System.out.println(list.size());
        System.out.println(hashPassword(user.getPassword()));
        user.setId(list.get(0).getId());

        if (list.size() == 1) {
            return true;
        } else {
            return false;
        }
    }



    public void addPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getPosts(Post post) {
        return postRepository.findAllByOrderByIdDesc();
    }

    /**
     * Password Hashing Method
     * @param password
     * @return
     */
    private String hashPassword(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }


}
