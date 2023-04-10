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
    @Size(max = 20, message = "제목 길이는 최대 20자입니다")
    private String title;

    @Size(max = 5000, message = "내용은 최대 5000자입니다")
    private String content;

    @NotBlank(message = "날짜를 지정해주세요")
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
