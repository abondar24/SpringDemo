package org.abondar.experimental.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"jwt.expTime=1"})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class JwtExpirationTest {


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Jackson2ObjectMapperBuilder.json().build();
    }

    @Test
    public void testGetSecret() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("user"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post("/security")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var resp =
                mockMvc.perform(post("/security/login")
                        .header("Authorization", "Basic "+new String(basicToken)))
                                .andReturn()
                                        .getResponse();
        var token = resp.getHeader("Authorization");

        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is(406));

    }
}
