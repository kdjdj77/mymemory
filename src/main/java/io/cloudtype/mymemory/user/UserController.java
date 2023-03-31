package io.cloudtype.mymemory.user;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.user.request.JoinRequest;
import io.cloudtype.mymemory.user.request.UpdateRequest;
import io.cloudtype.mymemory.user.response.DeleteResponse;
import io.cloudtype.mymemory.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public UserResponse join(@RequestBody @Validated JoinRequest request) {
        User user = request.toUser();

        return UserResponse.of(userService.upsertUser(user));
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody @Validated UpdateRequest request, @PathVariable("id") Long userId) {
        User user = userService.getUser(userId);
        request.update(user);
        return UserResponse.of(userService.upsertUser(user));
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable("id") Long userId) {
        User user = userService.getUser(userId);
        return UserResponse.of(user);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse delete(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return DeleteResponse.of(200);
    }
}
