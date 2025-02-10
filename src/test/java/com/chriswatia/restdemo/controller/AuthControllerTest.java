package com.chriswatia.restdemo.controller;

import com.chriswatia.restdemo.config.SecurityConfig;
import com.chriswatia.restdemo.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, TokenService.class})
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGenerateToken() throws Exception{
        this.mockMvc.perform((MockMvcRequestBuilders.post("/auth/token")
                .with(httpBasic("admin", "admin"))
        )).andExpect(status().isOk());
    }
}