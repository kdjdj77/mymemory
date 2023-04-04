package io.cloudtype.mymemory.memo.response;

import io.cloudtype.mymemory.memo.Memo;
import lombok.Data;

@Data
public class MemoResponse {
    private int status;
    private Long id;
    private String title;
    private String content;

    public static MemoResponse of(Memo memo) {
        MemoResponse response = new MemoResponse();
        response.setStatus(200);
        response.setId(memo.getId());
        response.setTitle(memo.getTitle());
        response.setContent(memo.getContent());
        return response;
    }
}
