package io.cloudtype.mymemory.memo.request;

import io.cloudtype.mymemory.memo.Memo;
import io.cloudtype.mymemory.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateRequest {
    @NotBlank(message = "제목을 설정해주세요")
    private String title;
    private String content;

    public void update(Memo memo) {
        memo.setTitle(this.title);
        memo.setContent(this.content);
    }
}
