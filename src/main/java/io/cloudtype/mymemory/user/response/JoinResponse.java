package io.cloudtype.mymemory.user.response;

import io.cloudtype.mymemory.user.User;
import lombok.Data;

@Data
public class JoinResponse {
    private int status;

    public static JoinResponse of(User user) {
        JoinResponse response = new JoinResponse();
        response.setStatus(200);
        return response;
    }
}
