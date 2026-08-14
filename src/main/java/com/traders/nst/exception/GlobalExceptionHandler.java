package com.traders.nst.exception;

import com.traders.nst.util.CommonUtilityFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.io.IOException;

import static com.traders.nst.exception.enums.ResponseErrorCodeEnum.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
                    implements AuthenticationEntryPoint {

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        log.error(ex.getMessage(),ex);
        return new ResponseEntity<>(CommonUtilityFunction.mapError(UNAUTHORIZED_ERROR.getErrorCode(),UNAUTHORIZED_ERROR.getMessage()), HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("Exception occurred: "+ex.getMessage(), ex);
        return new ResponseEntity<>(CommonUtilityFunction.mapError(SERVER_ERROR.getErrorCode(),SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    @ExceptionHandler(value = {AuthenticationException.class, UsernameNotFoundException.class, BadCredentialsException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.error("AuthenticationException occurred: "+authException.getMessage(), authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,INVALID_USER_CREDS.getMessage());
    }
}
