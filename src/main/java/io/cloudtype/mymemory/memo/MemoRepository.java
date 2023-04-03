package io.cloudtype.mymemory.memo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByUserIdAndDateBetween(Long userId, LocalDate firstDate, LocalDate lastDate);
}
