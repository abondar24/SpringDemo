package org.abondar.experimental.springsecurity.model;

import java.util.List;

public record UserOauthRequest(
        String sub,
        List<String> roles
) {
}
