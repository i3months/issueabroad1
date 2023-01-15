package issueabroad.first.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private Long ano;
    private String title;
    private String content;
    private String writerEmail;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;
}
