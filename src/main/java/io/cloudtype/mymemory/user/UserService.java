package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.config.JwtTokenProvider;
import io.cloudtype.mymemory.user.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User upsertUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new MyMemoryException(404, "해당하는 유저가 없습니다");
                });
        return user;
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new MyMemoryException(404, "해당하는 유저가 없습니다");
                });
        userRepository.deleteById(userId);
    }

    public String loginUser(User user) {
        User loginUser = userRepository.findByUsername(user.getUsername());
        if (loginUser == null) throw new MyMemoryException(404, "가입되지 않은 아이디입니다.");

        if (!bCryptPasswordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            throw new MyMemoryException(404, "비밀번호가 일치하지 않습니다");
        }
        // 로그인에 성공하면 email, roles 로 토큰 생성 후 반환
        return jwtTokenProvider.createToken(loginUser.getUsername());
    }
}
