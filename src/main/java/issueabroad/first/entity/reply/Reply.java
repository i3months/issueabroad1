package issueabroad.first.entity.reply;

import issueabroad.first.entity.article.Article2;
import issueabroad.first.entity.article.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String writer;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    private Article2 article;


}
