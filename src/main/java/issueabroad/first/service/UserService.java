package issueabroad.first.service;

import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;

public interface UserService {
    Long register(UserDTO dto);

    default User dtoToEntity(UserDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        User user = User.builder()
                .uno(dto.getUno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return user;
    }


}
