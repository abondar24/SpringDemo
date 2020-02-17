package org.abondar.experimental.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springboot.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getDemoTest() throws Exception {
        mockMvc.perform(get("/demo")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Spring Boot REST demo"));
    }


    @Test
    public void idTest() throws Exception {

        mockMvc.perform(get("/demo/id")
                .queryParam("id","7"))
                .andExpect(status().isOk())
                .andExpect(content().string("7 accepted"));

    }

    @Test
    public void postTest() throws Exception {

        Person person = new Person("Vasya", "Pupkin");
        person.setId(7);

        String body = objectMapper.writeValueAsString(person);

        mockMvc.perform(post("/demo/person/{id}","7")
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.id",is(7)))
                .andExpect(jsonPath("$.name",is("Vasya")))
                .andExpect(jsonPath("$.lastName",is("Pupkin")));

    }

    @Test
    public void exTest() throws Exception {


        mockMvc.perform(put("/demo/ex/{name}","Vasya"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }


    @Test
    public void exTestNotFound() throws Exception {

        mockMvc.perform(put("/demo/ex/{name}","Arsen"))
                .andExpect(status().isNotFound());

    }
}
