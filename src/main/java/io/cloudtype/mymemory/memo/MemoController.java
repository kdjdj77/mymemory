package io.cloudtype.mymemory.memo;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.memo.request.UpdateRequest;
import io.cloudtype.mymemory.memo.request.WriteRequest;
import io.cloudtype.mymemory.memo.response.DeleteResponse;
import io.cloudtype.mymemory.memo.response.MemoListResponse;
import io.cloudtype.mymemory.memo.response.MemoResponse;
import io.cloudtype.mymemory.user.User;
import io.cloudtype.mymemory.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/write")
    public MemoResponse write(
            @RequestBody @Validated WriteRequest request) {
        Memo memo = request.toMemo();

        return MemoResponse.of(memoService.writeMemo(memo));
    }

    @PutMapping("/{id}")
    public MemoResponse update(
            @RequestBody @Validated UpdateRequest request,
            @PathVariable("id") Long memoId) {
        Memo memo = memoService.getMemo(memoId);
        request.update(memo);
        return MemoResponse.of(memoService.upsertMemo(memo));
    }

    @DeleteMapping("/{userId}/{memoId}")
    public DeleteResponse delete(
            @PathVariable("memoId") Long memoId) {
        memoService.deleteMemo(memoId);
        return DeleteResponse.of(200);
    }

    @GetMapping("/{memoId}")
    public MemoResponse getMemo(
            @PathVariable("memoId") Long memoId) {
        Memo memo = memoService.getUserMemo(memoId);
        return MemoResponse.of(memo);
    }


    @GetMapping("/{year}/{month}")
    public MemoListResponse getMemoList(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month) {
        List<Memo> list = memoService.getMemoList(year, month);
        return MemoListResponse.of(list);
    }

    @GetMapping("/{year}/{month}/{day}")
    public MemoResponse getMemoByDate(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day) {
        Memo memo = memoService.getMemoByDate(year, month, day);
        return MemoResponse.of(memo);
    }
}
