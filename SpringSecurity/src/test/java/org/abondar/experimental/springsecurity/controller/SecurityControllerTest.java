package org.abondar.experimental.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.abondar.experimental.springsecurity.util.EndpointUtil;
import org.abondar.experimental.springsecurity.util.HeaderUtil;
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

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void testLoginUser() throws Exception {
        var req = new UserCreateRequest("test1", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("logged in")))
                .andExpect(header().string(HeaderUtil.AUTH_HEADER, containsString(HeaderUtil.BEARER_PREFIX)));
    }

    @Test
    public void testLoginUserUnauthorized() throws Exception {
        var basicToken = Base64.getEncoder().encode("test:test".getBytes());

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetSecret() throws Exception {
        var req = new UserCreateRequest("test2", "test", List.of("user"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);

        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Secret")));

    }

    @Test
    public void testGetSecretMultipleRoles() throws Exception {
        var req = new UserCreateRequest("test21", "test", List.of("user", "admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post("/security/login")
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);

        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Secret")));

    }

    @Test
    public void testGetSecretUnauthorized() throws Exception {
        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testGetSecretWrongToken() throws Exception {
        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, "Bearer: wrong token"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testGetSecretWrongRole() throws Exception {
        var req = new UserCreateRequest("test3", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);


        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isForbidden());

    }

    @Test
    public void testGetUltra() throws Exception {
        var req = new UserCreateRequest("test4", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);

        mockMvc.perform(get(EndpointUtil.SECURITY_PATH + EndpointUtil.ULTRA_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Ultrasecret")));

    }

    @Test
    public void testDelete() throws Exception {
        var req = new UserCreateRequest("test5", "test", List.of("admin"));

        var json = mapper.writeValueAsString(req);

        var created = mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var data = mapper.readValue(created, UserResponse.class);

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);

        mockMvc.perform(delete(EndpointUtil.SECURITY_PATH+"/" + data.id())
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User deleted")));

    }

    @Test
    public void testRefresh() throws Exception {
        var req = new UserCreateRequest("test21", "test", List.of("user", "admin"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var res = mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                        .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                .andReturn()
                .getResponse();

        var token = res.getHeader(HeaderUtil.AUTH_HEADER);


        mockMvc.perform(post(EndpointUtil.SECURITY_PATH+EndpointUtil.REFRESH_PATH)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Token refreshed")));
    }


}
