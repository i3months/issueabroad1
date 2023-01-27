package issueabroad.first.entity.article;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Scrap extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(columnDefinition = "TEXT")
    private String originContent;
    @Column(columnDefinition = "TEXT")
    private String originTitle;

    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String webSite;
    @Column(columnDefinition = "TEXT")
    private String url;

    private String type;
    private Long viewCount;

}
