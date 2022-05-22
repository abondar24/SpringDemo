package org.abondar.experimental.springsecurity.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record UserCreateRequest(
        @NotNull
        @NotBlank
        String login,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        List<String> roles
) {
}
