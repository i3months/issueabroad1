package issueabroad.first.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebUserDTO {

    private Long uno;

    private String content;
    private String title;

    private String type;

    private String writerEmail;
    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private int replyCount;
    private Long viewCount;

}
