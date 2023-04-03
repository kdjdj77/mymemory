package io.cloudtype.mymemory.memo;

import io.cloudtype.mymemory.MyMemoryException;
import io.cloudtype.mymemory.memo.response.MemoResponse;
import io.cloudtype.mymemory.user.User;
import io.cloudtype.mymemory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public Memo writeMemo(Long userId, Memo memo) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            memoRepository.save(memo);
        }
        return memo;
    }

    public Memo getMemo(Long memoId) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> {
                    return new MyMemoryException(404, "해당 일기가 없습니다.");
                });
        return memo;
    }

    public Memo upsertMemo(Memo memo) {
        return memoRepository.save(memo);
    }

    public void deleteMemo(Long userId, Long memoId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            memoRepository.deleteById(memoId);
        }
    }

    public void getMemoList(Long userId, Memo memo) {
        List<Memo> memoList = memoRepository.findByUserIdandMemoId(userId, memo.getUserId());
        if (memoList.size() == 0 || memoList == null) {
//            return new MyMemoryException(404, "일기 목록이 없습니다");
//        } else {

        }
    }
}
