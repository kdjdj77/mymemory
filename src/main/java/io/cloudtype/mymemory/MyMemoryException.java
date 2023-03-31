package io.cloudtype.mymemory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyMemoryException extends RuntimeException {
    private int status = 400;
    private String message;

    public MyMemoryException(String message) {
        this.message = message;
    }
}
