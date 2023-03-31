package io.cloudtype.mymemory.user.request;

import io.cloudtype.mymemory.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateRequest {

    @NotBlank(message = "별명을 설정해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$",
            message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String name;

    public void update(User user) {
        user.setName(this.name);
    }
}
