package issueabroad.first.entity.reply;

import issueabroad.first.entity.article.Article;
import issueabroad.first.entity.article.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "article")
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String text;
    private String replyer;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    private Article article;


}
