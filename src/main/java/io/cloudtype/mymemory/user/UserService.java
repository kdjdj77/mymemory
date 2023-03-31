package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User upsertUser(User user) {
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
