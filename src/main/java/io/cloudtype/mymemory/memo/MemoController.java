package io.cloudtype.mymemory.memo;

import io.cloudtype.mymemory.memo.request.UpdateRequest;
import io.cloudtype.mymemory.memo.request.WriteRequest;
import io.cloudtype.mymemory.memo.response.DeleteResponse;
import io.cloudtype.mymemory.memo.response.MemoResponse;
import io.cloudtype.mymemory.user.User;
import io.cloudtype.mymemory.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final UserService userService;

    @PostMapping("/write/{id}")
    public MemoResponse write(@RequestBody @Validated WriteRequest request, @PathVariable("id") Long userId) {
        Memo memo = request.toMemo();

        return MemoResponse.of(memoService.writeMemo(userId, memo));
    }

    @PutMapping("/{id}")
    public MemoResponse update(@RequestBody @Validated UpdateRequest request, @PathVariable("id") Long memoId) {
        Memo memo = memoService.getMemo(memoId);
        request.update(memo);
        return MemoResponse.of(memoService.upsertMemo(memo));
    }

    @DeleteMapping("/{userId}/{memoId}")
    public DeleteResponse delete(@PathVariable("userId") Long userId, @PathVariable("memoid") Long memoId) {
        memoService.deleteMemo(userId, memoId);
        return DeleteResponse.of(200);
    }



}
