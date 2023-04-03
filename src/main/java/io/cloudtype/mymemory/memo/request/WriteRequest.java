package io.cloudtype.mymemory.memo.request;

import io.cloudtype.mymemory.memo.Memo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class WriteRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;
    private String date;

    public Memo toMemo() {
        Memo memo = new Memo();
        memo.setTitle(this.title);
        memo.setContent(this.content);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        memo.setDate(LocalDate.parse(this.date, formatter));
        return memo;
    }
}
