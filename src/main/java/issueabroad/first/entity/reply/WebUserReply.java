package issueabroad.first.entity.reply;

import issueabroad.first.entity.article.BaseTimeEntity;
import issueabroad.first.entity.article.WebUser;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "user")
public class WebUserReply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urno;

    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "TEXT")
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private WebUser webUser;


}
