package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.common.U;
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
        User dupUser = userRepository.findByUsername(user.getUsername());
        if (dupUser != null) throw new MyMemoryException(404, "이미 존재하는 아이디입니다.");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser() {
        Long userId = U.getLoggedUser().getId();
        if (userId == null) throw new MyMemoryException(404, "잘못된 유저입니다.");
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new MyMemoryException(404, "올바르지 않은 유저입니다.");
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
        if (loginUser == null) throw new MyMemoryException(404, "일치하는 계정이 없습니다");

        if (!bCryptPasswordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            throw new MyMemoryException(404, "일치하는 계정이 없습니다");
        }
        // 로그인에 성공하면 email, roles 로 토큰 생성 후 반환
        return jwtTokenProvider.createToken(loginUser.getUsername());
    }
}
