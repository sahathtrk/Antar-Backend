package com.andree.antar_be.controller;

import com.andree.antar_be.shared.BaseResponse;
import com.andree.antar_be.shared.IError;
import com.andree.antar_be.utils.IException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class SpringRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return BaseResponse.responseError(IError.builder().code(status.value()).codeServer("400001").message(ex.getLocalizedMessage()).build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return BaseResponse.responseError(IError.builder().
                stack(Arrays.stream(Objects.requireNonNull(ex.getDetailMessageArguments())).collect(Collectors.toList())).
                code(400).message(ex.getBody().getDetail()).build());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Log and return
        System.out.println("Test");
        System.out.println(ex.getMessage());
        return BaseResponse.responseError(ex);
    }

    @ExceptionHandler(IException.class)
    public final ResponseEntity<Object> handleInternalExceptions(IException ex) {
        return BaseResponse.responseError(IError.builder()
                .message(ex.getLocalizedMessage())
                .code(ex.getStatus())
                .codeServer(ex.getCode())
                .build(), ex.getMessage(), 400);
    }
}
