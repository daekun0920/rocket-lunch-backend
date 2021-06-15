package com.project.rocketlunch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rocketlunch.controller.RocketLunchController;
import com.project.rocketlunch.model.User;
import com.project.rocketlunch.service.RocketLunchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RocketLunchBackendApplicationTests {
    @Autowired
    private RocketLunchController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RocketLunchService service;

    @Test
    void contextLoads() {

        User user = new User();

        user.setPassword("1234");
        user.setEmail("dosemfb0920@gmail.com");

        service.signUp(user);

        user.setPassword("1234");

        System.out.print("user id : " + user.getId());

        try {
            this.mockMvc.perform(post("/api/v1/rocket-lunch/signin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {

        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
