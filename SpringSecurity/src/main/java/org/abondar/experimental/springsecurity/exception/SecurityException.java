package org.abondar.experimental.springsecurity.exception;

public class SecurityException extends RuntimeException{
    private String msg;

    public SecurityException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
