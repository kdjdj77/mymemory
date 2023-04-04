package io.cloudtype.mymemory.user.response;

import lombok.Data;

@Data
public class TokenResponse {
    private int status;
    private String token;

    public static TokenResponse of(String token) {
        TokenResponse response = new TokenResponse();
        response.setStatus(200);
        response.setToken(token);
        return response;
    }
}
