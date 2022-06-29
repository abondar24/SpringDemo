package org.abondar.experimental.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"jwt.expTime=10"})
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
    public void testExpiration() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("user"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var resp =
                mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                                .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX + new String(basicToken)))
                        .andReturn()
                        .getResponse();
        var token = resp.getHeader(HeaderUtil.AUTH_HEADER);

        mockMvc.perform(get(EndpointUtil.SECURITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().is(406));

    }


    @Test
    public void testRefreshExpired() throws Exception {
        var req = new UserCreateRequest("test", "test", List.of("user"));

        var json = mapper.writeValueAsString(req);

        mockMvc.perform(post(EndpointUtil.SECURITY_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        var creds = req.login() + ":" + req.password();
        var basicToken = Base64.getEncoder().encode(creds.getBytes());

        var resp =
                mockMvc.perform(post(EndpointUtil.SECURITY_PATH + EndpointUtil.LOGIN_PATH)
                                .header(HeaderUtil.AUTH_HEADER, HeaderUtil.BASIC_PREFIX+ new String(basicToken)))
                        .andReturn()
                        .getResponse();
        var token = resp.getHeader(HeaderUtil.AUTH_HEADER);


        mockMvc.perform(post(EndpointUtil.SECURITY_PATH+EndpointUtil.REFRESH_PATH)
                        .header(HeaderUtil.AUTH_HEADER, token))
                .andExpect(status().isOk())
                .andExpect(header().string(HeaderUtil.AUTH_HEADER, containsString(HeaderUtil.BEARER_PREFIX)))
                .andExpect(jsonPath("$", is("Token refreshed")));

    }
}
