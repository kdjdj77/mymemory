package io.cloudtype.mymemory.memo.response;

import io.cloudtype.mymemory.memo.Memo;
import lombok.Data;

import java.util.List;

@Data
public class MemoListResponse {
    private int status;
    private Memo[] list;

    public static MemoListResponse of(List<Memo> list) {
        MemoListResponse response = new MemoListResponse();
        response.setStatus(200);

        Memo[] res = new Memo[32];
        for(Memo m : list) res[m.getDate().getDayOfMonth()] = m;
        response.setList(res);

        return response;
    }
}
