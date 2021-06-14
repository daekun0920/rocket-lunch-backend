package com.project.rocketlunch.controller;

import com.project.rocketlunch.model.User;
import com.project.rocketlunch.service.RocketLunchService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
    @PostMapping(path = "/signin")
    public ResponseEntity signInUser(@RequestBody User user) {
        try {
            System.out.println(user.getEmail() + " : " + user.getPassword());
            if (rocketLunchService.signIn(user)) {
                return ResponseEntity.accepted().body("Success");
            } else {
                throw new Exception("Found more than 1 user.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
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

            return ResponseEntity.accepted().body("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

}
