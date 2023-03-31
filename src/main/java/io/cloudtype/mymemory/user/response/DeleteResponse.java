package io.cloudtype.mymemory.user.response;

import io.cloudtype.mymemory.user.User;
import lombok.Data;

@Data
public class DeleteResponse {
    private int status;
    private String message;

    public static DeleteResponse of(int status) {
        DeleteResponse response = new DeleteResponse();
        response.setStatus(status);
        response.setMessage("회원 탈퇴 성공");
        return response;
    }
}
