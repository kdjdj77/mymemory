package io.cloudtype.mymemory.user.request;

import io.cloudtype.mymemory.user.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@Data
public class JoinRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "아이디는 4~10자만 가능합니다")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "별명을 설정해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$",
            message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String name;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        return user;
    }
}
