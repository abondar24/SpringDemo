package org.abondar.experimental.springsecurity.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PasswordException.class)
    public void handlePassword(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN,ex.getMessage());
    }

}
