package io.cloudtype.mymemory.memo.response;

import lombok.Data;

@Data
public class DeleteResponse {
    private int status;
    private String message;

    public static DeleteResponse of(int status) {
        DeleteResponse response = new DeleteResponse();
        response.setStatus(status);
        response.setMessage("글 삭제 성공");
        return response;
    }
}
