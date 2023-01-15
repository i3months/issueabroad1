package issueabroad.first.entity.article;

import issueabroad.first.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Article extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;
    private String title;
    private String content;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    private String type;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
