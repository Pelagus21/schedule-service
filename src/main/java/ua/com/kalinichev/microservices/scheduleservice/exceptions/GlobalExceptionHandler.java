package ua.com.kalinichev.microservices.scheduleservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(NoSuchElementException ex){
        Map<String, String> result = new HashMap<>();
        result.put("error", ex.getMessage());
        return result;
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequestException(BadRequestException ex){
        Map<String, String> result = new HashMap<>();
        result.put("error", ex.getMessage());
        return result;
    }

}
