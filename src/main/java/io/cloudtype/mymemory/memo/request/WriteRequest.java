package io.cloudtype.mymemory.memo.request;

import io.cloudtype.mymemory.memo.Memo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WriteRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Memo toMemo() {
        Memo memo = new Memo();
        memo.setTitle(title);
        memo.setContent(content);
        return memo;
    }

}
