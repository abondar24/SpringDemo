package org.abondar.experimental.springsecurity.model;

import java.util.List;

public record UserData(

        String id,
        String login,
        String hash,
        List<String> roles
) {
}
