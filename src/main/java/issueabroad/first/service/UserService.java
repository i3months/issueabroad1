package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;

public interface UserService {
    Long register(UserDTO dto);

    PageResultDTO<UserDTO, Object[]> getListAll(PageRequestDTO pageRequestDTO);
    UserDTO get(Long uno);
    void removeWithReplies(Long uno);

    default UserDTO entityToDTO(User user, Member member, Long replyCount) {
        UserDTO userDTO = UserDTO.builder()
                .uno(user.getUno())
                .title(user.getTitle())
                .content(user.getContent())
                .regDate(user.getRegDate())
                .modDate(user.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .viewCount(user.getViewCount())
                .type(user.getType())
                .replyCount(replyCount.intValue())
                .build();

        return userDTO;

    }

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
