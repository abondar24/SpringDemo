package org.abondar.experimental.springsecurity.model;

import java.util.List;

public record UserData(
        String login,
        String hash,
        List<String> roles
) {
}
