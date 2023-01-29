package issueabroad.first.service;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.entity.article.User;
import issueabroad.first.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    Long register(UserDTO dto);

    PageResultDTO<UserDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    PageResultDTO<UserDTO, Object[]> getListMain(PageRequestDTO pageRequestDTO);
    PageResultDTO<UserDTO, Object[]> getListSuggest(PageRequestDTO pageRequestDTO);
    PageResultDTO<UserDTO, Object[]> getListMainSuggest(PageRequestDTO pageRequestDTO);
    PageResultDTO<UserDTO, Object[]> getListFree(PageRequestDTO pageRequestDTO);
    PageResultDTO<UserDTO, Object[]> getListMainFree(PageRequestDTO pageRequestDTO);


    @Transactional
    public int updateViewCount(Long uno);

    UserDTO get(Long uno);
    void removeWithReplies(Long uno);
    void modify(UserDTO userDTO);

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
                .type(dto.getType())
                .viewCount(dto.getViewCount())
                .build();

        return user;
    }


}
