package issueabroad.first.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserReplyDTO {

    private Long urno;

    private String text;
    private String replyer;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private Long uno;
}
