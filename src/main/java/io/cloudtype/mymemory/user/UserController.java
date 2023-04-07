package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.user.request.JoinRequest;
import io.cloudtype.mymemory.user.request.LoginRequest;
import io.cloudtype.mymemory.user.request.UpdateRequest;
import io.cloudtype.mymemory.user.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Validated LoginRequest request) {
        User user = request.loginUser();
        return TokenResponse.of(userService.loginUser(user));
    }

    @PostMapping("/join")
    public JoinResponse join(@RequestBody @Validated JoinRequest request) {
        User user = request.toUser();
        return JoinResponse.of(userService.upsertUser(user));
    }

    @GetMapping("/users/isValid")
    public ValidResponse isValid() {
        return ValidResponse.of(200);
    }

    @PutMapping("/users/{id}")
    public UserResponse update(@RequestBody @Validated UpdateRequest request, @PathVariable("id") Long userId) {
        User user = userService.getUser(userId);
        request.update(user);
        return UserResponse.of(userService.upsertUser(user));
    }

    @GetMapping("/users/{id}")
    public UserResponse get(@PathVariable("id") Long userId) {
        User user = userService.getUser(userId);
        return UserResponse.of(user);
    }

    @DeleteMapping("/users/{id}")
    public DeleteResponse delete(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return DeleteResponse.of(200);
    }
}
