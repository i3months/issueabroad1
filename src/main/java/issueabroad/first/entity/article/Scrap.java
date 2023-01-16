package issueabroad.first.entity.article;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;
    private String originContent;
    private String originTitle;
    private String webSite;
    private String url;

    @OneToOne
    private Article article;

}
