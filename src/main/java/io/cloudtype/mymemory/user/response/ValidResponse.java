package io.cloudtype.mymemory.user.response;

import io.cloudtype.mymemory.user.User;
import lombok.Data;

@Data
public class ValidResponse {
    private int status;
    public static ValidResponse of(int status) {
        ValidResponse response = new ValidResponse();
        response.setStatus(status);
        return response;
    }
}
