package io.cloudtype.mymemory.memo;

import io.cloudtype.mymemory.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "memos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Memo extends BaseEntity {
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "content", length=5000)
    private String content;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
