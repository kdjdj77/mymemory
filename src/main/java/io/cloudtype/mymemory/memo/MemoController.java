package io.cloudtype.mymemory.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;



}
