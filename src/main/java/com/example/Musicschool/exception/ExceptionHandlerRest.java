package com.example.Musicschool.exception;

import com.example.Musicschool.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerRest {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<Map<String,String>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DatabaseException.class)
    public ResponseDto<Map<String,String>> handleBusinessException(DatabaseException e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }
}
