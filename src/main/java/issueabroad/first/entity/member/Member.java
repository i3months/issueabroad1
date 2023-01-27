package issueabroad.first.entity.member;

import issueabroad.first.entity.article.BaseTimeEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseTimeEntity {

    @Id
    private String email;

    private String password;
    private String name;

}
