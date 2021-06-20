package com.project.rocketlunch.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.project.rocketlunch.model.ChatRoom;
import com.project.rocketlunch.model.Post;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.repository.ChatRoomRepository;
import com.project.rocketlunch.repository.PostRepository;
import com.project.rocketlunch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RocketLunchService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    /**
     * Sign Up Service
     * @param user
     */
    public void signUp(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getId());
    }


    /**
     * Sign In Service
     * @param user
     * @return
     */
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


    /**
     * Add Post Service
     * @param post
     */
    public void addPost(Post post) {
        postRepository.save(post);
    }

    /**
     * Get Posts Service
     * @param city
     * @return
     */
    public List<Post> getPosts(String city) {
        // if City parameter is presented(When searched by an user)
        if (!Strings.isNullOrEmpty(city)) {
            return postRepository.getAllPostsByCity(city);
        } else {
            return postRepository.getAllPosts();
        }
    }

    /**
     * Update User Info
     * @param user
     */
    public void userUpdate(User user) {
        Optional<User> tempUser = userRepository.findById(user.getId());
        tempUser.ifPresent((data) -> {
            System.out.println(data.getPassword());
            System.out.println(data.getId());
            System.out.println(data.getUsername());

            data.setId(user.getId());
            data.setUsername(user.getUsername());
            data.setPassword(hashPassword(user.getPassword()));
            userRepository.save(data);
        });
    }

    /**
     * Make Chat Room
     * @param chatRoom
     */
    public void makeRoom(ChatRoom chatRoom) {
        chatRoomRepository.saveAndFlush(chatRoom);
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
