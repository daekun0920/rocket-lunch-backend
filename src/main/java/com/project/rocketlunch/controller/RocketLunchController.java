package com.project.rocketlunch.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.project.rocketlunch.model.Post;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.service.RocketLunchService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/posts")
    public ResponseEntity getPosts(Post post) {
        try {
            List<Post> list = rocketLunchService.getPosts(post);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }

    }

}
