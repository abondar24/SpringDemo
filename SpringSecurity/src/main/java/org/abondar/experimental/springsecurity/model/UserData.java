package org.abondar.experimental.springsecurity.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserData(
        @NotNull
        @NotBlank
        String login,

        @NotNull
        @NotBlank
        String password
) {
}
