package com.project.rocketlunch.controller;

import com.project.rocketlunch.model.ChatRoom;
import com.project.rocketlunch.model.Post;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.service.RocketLunchService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rocket-lunch")
@CrossOrigin("*")
public class RocketLunchController {

    @Autowired
    RocketLunchService rocketLunchService;

    /**
     * User Sign In
     * @param user
     * @return
     */
    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signInUser(@RequestBody User user) {
        try {
            System.out.println(user.getEmail() + " : " + user.getPassword());
            if (rocketLunchService.signIn(user)) {
                return ResponseEntity.ok().body(user);
            } else {
                throw new Exception("Found more than 1 user.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    /**
     * User Sign Up
     * @param user
     * @return
     */
    @PostMapping(path = "/signup")
    public ResponseEntity signUpUser(@RequestBody User user) {
        try {
            rocketLunchService.signUp(user);

            return ResponseEntity.ok().body("Success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    /**
     * Add Post
     * @param post
     * @return
     */
    @PostMapping(path = "/addpost")
    public ResponseEntity addPost(@RequestBody Post post) {
        try {
            rocketLunchService.addPost(post);

            return ResponseEntity.ok().body(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    /**
     * Get Posts
     * @param city
     * @return
     */
    @GetMapping(path = "/posts")
    public ResponseEntity getPosts(@RequestParam(required = false) String city) {
        try {
            List<Post> list = rocketLunchService.getPosts(city);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping(path = "/user-update")
    public ResponseEntity userUpdate(@RequestBody User user) {
        try {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            rocketLunchService.userUpdate(user);
            return ResponseEntity.ok().body("Success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping(path = "/make-room")
    public ResponseEntity makeRoom(@RequestBody ChatRoom chatRoom) {
        try {
            rocketLunchService.makeRoom(chatRoom);
            return ResponseEntity.ok().body("Success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
