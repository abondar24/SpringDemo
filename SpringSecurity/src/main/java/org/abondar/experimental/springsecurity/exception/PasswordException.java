package org.abondar.experimental.springsecurity.exception;

public class PasswordException extends RuntimeException{

    private String msg;

    public PasswordException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
