package issueabroad.first.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberForm {

    private String email;
    private String name;
    private String password;
}
