package io.cloudtype.mymemory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MyMemoryException.class)
    public ErrorResponse handle(HttpServletResponse response, MyMemoryException e) {
        response.setStatus(e.getStatus());
        return new ErrorResponse(e.getStatus(), e.getMessage());
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private int status;
        private String message;
    }
}
