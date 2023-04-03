package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
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
}
