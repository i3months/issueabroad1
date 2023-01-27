package issueabroad.first.entity.reply;

import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.article.BaseTimeEntity;
import issueabroad.first.entity.article.Scrap;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "scrap")
public class ScrapReply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long srno;

    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "TEXT")
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Scrap scrap;


}
