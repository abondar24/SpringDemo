package org.abondar.experimental.springsecurity.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class OauthTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtDecoder nimbusDecoder;

    @Test
    public void testOauthGetSecret() throws Exception {

        var testOauthToken = "test";

        var resourceAccess = new JSONObject();
        var roles = new JSONObject();
        roles.put("roles", List.of("user"));
        resourceAccess.put("spring",roles);

        var accessToken = new Jwt("test", Instant.now(), Instant.MAX,
                Map.of("alg", "RS256",
                        "typ", "JWT",
                        "kid", "ci5rjgHEh6I87ik84mTMzAZVR43RLDkwP-gOq12zhpM"
                ),
                Map.of("sub", "test", "resource_access", resourceAccess));

        when(nimbusDecoder.decode(testOauthToken)).thenReturn(accessToken);

        mockMvc.perform(get("/security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-OAUTH-TOKEN", testOauthToken))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", containsString("Bearer ")));

    }

}
