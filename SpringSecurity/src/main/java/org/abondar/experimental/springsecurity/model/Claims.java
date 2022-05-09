package org.abondar.experimental.springsecurity.model;

public enum Claims {
    ROLE_CLAIM("roles"),
    PWD_CLAIM("pwd"),
    ISS_CLAIM("iss"),
    AUD_CLAIM("aud"),
    SUB_CLAIM("sub"),
    EXP_CLAIM("exp");




    private final String val;

    Claims(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
