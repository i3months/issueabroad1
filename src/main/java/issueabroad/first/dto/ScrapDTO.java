package issueabroad.first.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScrapDTO {

    private Long sno;

    private String originContent;
    private String originTitle;

    private String content;
    private String title;

    private String webSite;
    private String url;

    private String type;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private int replyCount;
    private Long viewCount;

}
