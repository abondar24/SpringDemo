package org.abondar.experimental.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private SecurityController controller;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Jackson2ObjectMapperBuilder.json().build();
    }

    @Test
    public void testCreateUser() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", notNullValue()));
            //    .andExpect(header().string("Authorization", containsString("Bearer: ")));
    }

    @Test
    @Disabled
    public void testGetSecret() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("user"));

        var json = mapper.writeValueAsString(req);

        var res = mockMvc.perform(post("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();

        var token = res.getHeader("Authorization");

        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Secret")));

    }

    @Test
    public void testGetSecretUnauthorized() throws Exception {
        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @Disabled
    public void testGetSecretWrongToken() throws Exception {
        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer: wrong token"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @Disabled
    public void testGetSecretWrongRole() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        var res = mockMvc.perform(post("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();

        var token = res.getHeader("Authorization");

        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());

    }

    @Test
    @Disabled
    public void testGetUltra() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        var res = mockMvc.perform(post("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();

        var token = res.getHeader("Authorization");

        mockMvc.perform(get("/security/ultra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Ultrasecret")));

    }

    @Test
    @Disabled
    public void testDelete() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        var res = mockMvc.perform(post("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();

        var token = res.getHeader("Authorization");
        var userResp = res.getContentAsString();
        var userId = mapper.readValue(userResp, UserResponse.class).id();

        mockMvc.perform(delete("/security/" + userId)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User deleted")));

    }


}
