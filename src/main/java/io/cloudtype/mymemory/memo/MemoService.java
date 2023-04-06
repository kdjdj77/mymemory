package io.cloudtype.mymemory.memo;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.common.U;
import io.cloudtype.mymemory.memo.response.MemoResponse;
import io.cloudtype.mymemory.user.User;
import io.cloudtype.mymemory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public Memo writeMemo(Memo memo) {
        Long userId = getUserId();
        Memo exist = memoRepository.findByUserIdAndDate(userId, memo.getDate());
        if (exist != null) {
            exist.setTitle(memo.getTitle());
            exist.setContent(memo.getContent());
            memo = exist;
        } else memo.setUserId(userId);
        
        memoRepository.save(memo);
        return memo;
    }

    public Memo getMemo(Long memoId) {
        Long userId = getUserId();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> {
                    return new MyMemoryException(404, "해당 일기가 없습니다.");
                });
        isMyMemo(userId, memo);
        return memo;
    }

    public Memo getUserMemo(Long memoId) {
        Long userId = getUserId();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> {
                    return new MyMemoryException(404, "해당 일기가 없습니다.");
                });
        if (memo.getUserId() != userId) throw new MyMemoryException(404, "접근할 수 없습니다.");
        return memo;
    }

    public Memo upsertMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public void deleteMemo(Long memoId) {
        Long userId = getUserId();
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> {
            return new MyMemoryException(404, "해당하는 일기가 없습니다.");
        });
        isMyMemo(userId, memo);
        memoRepository.deleteById(memoId);
    }

    public List<Memo> getMemoList(Integer year, Integer month) {
        Long userId = getUserId();
        LocalDate firstDate = LocalDate.of(year, month, 1);
        LocalDate lastDate = firstDate.withDayOfMonth(firstDate.lengthOfMonth());

        List<Memo> memoList = memoRepository.findByUserIdAndDateBetween(userId, firstDate, lastDate);
        return memoList;
    }
    private Long getUserId() {
        Long userId = U.getLoggedUser().getId();
        if (userId == null) throw new MyMemoryException(404, "잘못된 유저입니다.");
        userRepository.findById(userId).orElseThrow(() -> {
            return new MyMemoryException(404, "올바르지 않은 유저입니다.");
        });
        return userId;
    }
    private void isMyMemo(Long userId, Memo memo) {
        if (userId != memo.getUserId()) throw new MyMemoryException(404, "권한이 없습니다.");
    }

    public Memo getMemoByDate(Integer year, Integer month, Integer day) {
        LocalDate date = LocalDate.of(year, month, day);
        Long userId = getUserId();
        Memo memo = memoRepository.findByUserIdAndDate(userId, date);
        if (memo == null) throw new MyMemoryException(404, "일기 없음");
        return memo;
    }
}
