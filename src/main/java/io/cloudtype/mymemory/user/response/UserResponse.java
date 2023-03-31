package io.cloudtype.mymemory.user.response;

import io.cloudtype.mymemory.user.User;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserResponse {

    public Long id;
    private String username;
    private String name;

    public static UserResponse of(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setName(user.getName());
        return response;
    }
}
