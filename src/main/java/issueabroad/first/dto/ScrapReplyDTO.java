package issueabroad.first.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScrapReplyDTO {

    private Long srno;

    private String text;
    private String replyer;
    private String replyerEmail;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private Long sno;
}
